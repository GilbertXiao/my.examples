#!/bin/perl

$debugme = 1; #Will print Debug statments to STDOUT 
              #(use the Load Sourceuserexit's Redirect option to capture to a file)


#Handle the command-line arguments passed in by Load Utility
#-----------------------------------------------------------
print "[@ARGV]\n";

if ($#ARGV != 5)
{
  print "Invalid arguments :\n@ARGV\n\n<base pipename> <number of source media> ";
  print "<source media 1> <source media 2> ... <user exit ID> <number of user exits> ";
  print "<database partition number>\n";
  die;
}
if ($ARGV[1] != 1)
{
  if ($debugme) { print "This userexit only handles 1 source media file"; }
  die;
}

$basePipeName = $ARGV[0];
$datafile = $ARGV[2];
$outputFile = sprintf("%s.000", $basePipeName);


# Grab the arguments from STDIN and clean them
#---------------------------------------------
open(MYSTDIN, "-");
$input_stdin = <MYSTDIN>;
close(MYSTDIN);

# Seperate the input arguments
# example args: "colninds:(2 4,5 12,13 18,19 26,27 40) nullinds:(41,0,0,42,43)"
$input_stdin =~ /colninds:(.*)[\s]*nullinds:(.*)[\s]*/;
$colninds = $1;
$nullinds = $2;
if ($debugme) { 
	print "input=$input_stdin : colninds=$colninds : nullinds=$nullinds\n";	
}

# Trash the open and close brackest '(' and ')'
if ($colninds =~ /^[\s]*\((.*)\)[\s]*$/)
{
  $colninds = $1;
}
if ($nullinds =~ /^[\s]*\((.*)\)[\s]*$/)
{
  $nullinds = $1;
}
if ($debugme) { print "colninds=$colninds : nullinds=$nullinds\n"; }

# Split the input arguments into arrays
@colnpairs = split(/,/, $colninds);
@nullindsarr = split(/,/, $nullinds);

# Split each column-pair array element into first/last pairs
$colcount=0;
foreach $pair (@colnpairs)
{
  $pair =~ /([\w]*)[\s]*([\w]*)/;

  #The colinds and nullinds are 1 based, but Perl's substr is 0 bases, so minus 1.
  $colbegn[$colcount] = $1 - 1;
  $colend[$colcount] = $2 - 1;
  $collen[$colcount] = ($colend[$colcount] - $colbegn[$colcount]) + 1;

  if ($debugme) { 
  	print "colbegn[$i]=$colbegn[$colcount] : colend[$i]=$colend[$colcount] : ";
  	print "collen[$i]=$collen[$colcount]\n"; 
  }

  $colcount++;
}

#open source datafile
#--------------------
open(DATAFILE, '<', $datafile) || die "Could not open $datafile";

#open the output pipe (the Load utility is reading from this)
#------------------------------------------------------------
open(PIPETOLOAD, '>', $outputFile) || die "Could not open $outputFile";

# Read a line of input data at a time (this ASC file is new-line delimited, 
# otherwise we would have had to pass-in RECLEN and read that much).
#--------------------------------------------------------------------------
while ($line = <DATAFILE>)
{
  if ($debugme) { print "line=$line\n"; }
 
  # Ignore empty lines
  if ($line eq "\n") { next; } 

  # Remove newline character
  # (chomp actually removes the input record seperator $/ )
  chomp($line);
  
  # For each column
  for ($i = 0; $i < $colcount; $i++)
  {
    if ($debugme) { print "nullindarr[$i]=$nullindsarr[$i]\n"; }

    # If the nullind entry for this column is non-zero, then append a 
    # nullindicator to the end of the line
    #---------------------------------------------------------------
    if ($nullindsarr[$i] != 0)
    {
      #Examine the data between colbegn[i] and colend[i] to look for all blanks
      $thiscol = substr $line, $colbegn[$i], $collen[$i];
 
      if ($debugme) { print "begn=$colbegn[$i] : len=$collen[$i]: thiscol=$thiscol\n"; }

      #Add the the nullindicator
      if ($thiscol =~ /^[\s]*$/)
      {
        $line = $line.'Y';
      }
      else
      {
        $line = $line.'N';
      }
    }
  }

  # Add a newline character after all nullindicators have been added
  $line = $line."\n";

  if ($debugme) { print "final line=$line\n"; }

  # Send this record to Load for processing
  print PIPETOLOAD $line;
}

