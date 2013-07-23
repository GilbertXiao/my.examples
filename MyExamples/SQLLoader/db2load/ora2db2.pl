#!/usr/bin/perl
###################################################################################################
#
#  Author:      Vikram Khatri (vikram.khatri@us.ibm.com)
#
# (C) COPYRIGHT International Business Machines Corp. 2006-2007
#
# All Rights Reserved
#
# This software (“Software”) is owned by International Business Machines Corporation or one of its 
# subsidiaries ("IBM") and is copyrighted and licensed, not sold. This Software is not part of any 
# standard IBM product. You may use, copy, modify, and distribute this Software in any form without 
# payment to IBM, for the purpose of assisting you in the development of your applications. This 
# Software is provided to you on an "AS IS" basis, without warranty of any kind. IBM HEREBY EXPRESSLY 
# DISCLAIMS ALL WARRANTIES, EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
# WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. Some jurisdictions do not allow 
# for the exclusion or limitation of implied warranties, so the above limitations or exclusions may not 
# apply to you. IBM shall not be liable for any damages you suffer as a result of using, copying, 
# modifying or distributing this Software, even if IBM has been advised of the possibility 
# of such damages. 
#
# US Government Users Restricted Rights - Use, duplication or disclosure restricted 
# by GSA ADP Schedule Contract with IBM Corp.
#
###################################################################################################
#  Script:      ora2db2.pl
#  Date:        02/15/2008
#
#  Syntax:      perl ora2db2.pl -c <controlfile> [options]
#
#
#  Description: Conversion of Oracle control file (*.ctl) to DB2 load file
#
###################################################################################################

use Text::ParseWords;
use Cwd;

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# initialization
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
$data_file_name  = 'INPUT_FILE';
$schema_name     = 'ORA_TWIN';
$filename        = '';
$infile_name     = '';
$table_name      = '';
$mode            = '';
$i               = 0;
$column          = 0;
$timestampformat = '"YYYY-MM-DD"';
$constant        = 0;
$defaultif       = 0;
$dqstring        = 0;
$nullif          = 0;
$delimiter       = ';';
$method          = 'L';
$nullif_started  = 0;

$ostype          = 'Unix';
$type            = 'LOAD';
$mode            = 'INSERT';
$filetype        = 'ASC';
$defaultif_clause = 'NULL';
$msg_dir         = 'MSG_DIR';
$dump_dir        = 'DUMP_DIR';

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# functions
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

sub expand_space {
    $_ = $_[0];
    
    my $holdstr = '';
    my $first = '';
    my $second = '';
    my $third = '';
    my $noquote = 0;
    
    $string = $_;
    while( $string =~ /(["'])(?:\\\1|.)*?\1/ ){
       #print "Prior is: $` Match is: $& Later is: $'\n";
       $first = $`; 
       $second = $&;
       $third = $';
       $string = $';
	   $first =~ s/,/ , /g;
	   if  (not ($first =~ /POSITION|CHAR/i)) {
           $first =~ s/\(/ \( /g;
           $first =~ s/\)/ \) /g;            	        	
       } 		    
	   $first =~ s/\=/ \= /g; 
	   $third =~ s/,/ , /g;
	   if  (not ($third =~ /POSITION|CHAR/i)) {
           $third =~ s/\(/ \( /g;
           $third =~ s/\)/ \) /g;            	        	
       } 		    
	   $third =~ s/\=/ \= /g; 
	   $holdstr = $holdstr . $first . $second;
	   $noquote = 1; 
    }
	$holdstr = $holdstr . $third;    
    if ($noquote == 0) {
        $holdstr = $_;
        $holdstr =~ s/,/ , /g;   
        if  (not ($holdstr =~ /POSITION|CHAR/i)) {
           $holdstr =~ s/\(/ \( /g;
           $holdstr =~ s/\)/ \) /g;            	        	
        } 	
        $holdstr =~ s/\=/ \= /g;
    }    
    
    return $holdstr;    
}

sub read_oractlfile {
	$delim = '(\s+)';
	$i     = 1;
	open( CTLFILE, $_[0] );
	@ctl_lines = <CTLFILE>;
	foreach (@ctl_lines) {
		if (substr($_,0,2) ne '--') {
			$_ = expand_space($_);
			@words = &quotewords( $delim, 1, $_ );	
			foreach (@words) {
			    outside:
				if ($_ ne '') {
			       if ($nullif_started == 1) {
			       	  if (/^\(/) {
				        $_ =~ s/\(//g;
			       	  } else {			       	  				     
			       	  	$nullif_started = 0;
			       	  }
			          if (s/\)//g) {
			      	    $nullif_started = 0;				      	
			          }			       	  
				      if ($_ eq '') {
				      	 goto outside;
				      }
			       }
			       $array[$i++] = $_; 
				   if('NULLIF' eq uc($_)) {
				   	  $nullif_started = 1;
				   }
			    }
			}
		}
	}
	$max_line = $i - 1;
	close(CTLFILE);
}

sub parse_into {
	my @tabs;
	my $cnttab;
	if ('unix' eq lc($ostype)) {
       $dirsep = '/';        
	} else {
	   $dirsep = '\\';
	}
	$i++;
	$i++;
	$table_name = $array[$i];
	@tabs = split('\.',$table_name);
	$cnttab  = @tabs;
	if ($cnttab > 1) {
		$table_name = $tabs[1];
	}
    $messagefile_name = $msg_dir . $dirsep . lc ($schema_name) . '_' . lc($table_name) . ".msg";
    $dumpfile_name = $dump_dir . $dirsep . lc ($schema_name) . '_' . lc($table_name) . ".dump";
    #print $dumpfile_name . ":" . $messagefile_name . "\n";
}

sub parse_infile {
	$i++;
	if ($data_file_name eq '') {
	   $infile_name = $array[$i];
	} else {
	   $infile_name = "\"" . $data_file_name . "\"";
    }    
}

sub parse_delimiter {
	$i++;
	$i++;
	$i++;
    	
    $delimiter = $array[$i];	
	if ($delimiter =~ /^X/i) {
		$delimiter = sprintf("%02s", substr($array[$i],2,1));
	} else
	{
		$delimiter = ascii_to_hex(substr($array[$i],1,1));
	}
	$method = 'P';
	$filetype = 'DEL';
}

sub set_mode {
	if ( '' eq $mode ) {
		if ($_[0] eq 'INSERT') { 
		   $mode = 'INSERT';
		} elsif ($_[0] eq 'APPEND') { 
		   $mode = 'INSERT';
	    } elsif ($_[0] eq 'REPLACE') {
		   $mode = 'REPLACE';
        } elsif ($_[0] eq 'TRUNCATE') { 
		   $mode = 'REPLACE';
        } else { 
		   $mode = 'REPLACE';
		}
	}
}

sub parse_field_list {
	while ($array[$i] ne ")") {
		$i++;
	    my $colname = $array[$i];
		$i++;
		@position = split( /([:()])/, $array[$i] );
		if ($method eq 'L') {
	        if ($position[0] ne 'CONSTANT') {
	            $column++;
				$field_list[$column][1] = $colname;    # 1. column name
			    $field_list[$column][2] = $position[2];    # 2. from column
			    $field_list[$column][3] = $position[4];    # 3. up to column			
	 	        #print $field_list[$column][1] . ":" . $field_list[$column][2] . ":" . $field_list[$column][3] . "\n";	        	
	        }
		} else {
	        $column++;
			$field_list[$column][1] = $colname;    # 1. column name			
		}
		while (( "," ne $array[$i] ) && 
		       "DATE" ne uc($array[$i]) &&
		       "DEFAULTIF" ne uc($array[$i]) &&
		       "CONSTANT" ne uc($array[$i]) &&
		       "NULLIF" ne uc($array[$i]) &&
		       (not($array[$i] =~ /(["'])(?:\\\1|.)*?\1/)) &&
		       ")" ne $array[$i]) {
			#print $array[$i] . "\n"; 
			$i++;
		}
		if ($array[$i] =~ /(["'])(?:\\\1|.)*?\1/) {
			$dqstring++;
			$dqstr_list[$dqstring][1] = $colname;
			$dqstr_list[$dqstring][2] = $array[$i];
		    #print $dqstr_list[$dqstring][1] . ":" . $dqstr_list[$dqstring][2] . "\n";
		} 
		if ('DATE' eq $array[$i]) {
			$timestampformat = $array[$i+1];
			if ($timestampformat eq q{"YYYY-MM-DD-HH24.MI.SS"} || 
			    $timestampformat eq q{"YYYY-MM-DD HH24.MI.SS"}) {
				$timestampformat = q{"YYYY-MM-DD-HH.MM.SS"};
			}
		} 
		if ('CONSTANT' eq $array[$i]) {
			$constant++;
			$constant_list[$constant][1] = $array[$i-1];
			$constant_list[$constant][2] = $array[$i+1];
		    #print $constant_list[$constant][1] . ":" . $constant_list[$constant][2] . "\n";
		} 
		if ('DEFAULTIF' eq $array[$i]) {
			$defaultif++;
			$default_list[$defaultif][1] = $array[$i+1];
			$default_list[$defaultif][2] = $array[$i+3];
		    #print $default_list[$defaultif][1] . ":" . $default_list[$defaultif][2] . "\n";
		}
		if ('NULLIF' eq $array[$i]) {
			$nullif++;
			$nullif_list[$nullif][1] = $array[$i+1];
			$nullif_list[$nullif][2] = $array[$i+3];
			$nullif_list[$nullif][3] = uc($array[$i-1]);
		    #print $nullif_list[$nullif][1] . ":" . $nullif_list[$nullif][2] . "\n";
		}
		while (( "," ne $array[$i] ) && 
		       ")" ne $array[$i]) {
			#print $array[$i] . "\n"; 
			$i++;
		}
	}
}

sub ascii_to_hex ($)
{
  	## Convert each ASCII character to a two-digit hex number.
  	(my $str = shift) =~ s/(.|\n)/sprintf("%02lx", ord $1)/eg;
  	return $str;
}

sub usage {
	printf("USAGE: perl ora2db.pl -c controlfile [options]\n");
    printf("\t-o ostype (Unix|Windows - default is Unix. Other value is Windows\n");
    printf("\t-m message_directory_name (default is MSG_DIR)\n");
    printf("\t-e dump_directory_name (default is DUMP_DIR). This dir should reside on all partitions on DB2 server.\n");
    printf("\t-d data_file_name (default is INPUT_FILE)\n");
    printf("\t-f defaultif clause set to either (default|null - default is null)\n");
    printf("\t-s schema name. Replace with the schame name in control file\n");
    printf("\t-g timestamp format. Default is YYYY-MM-DD\n");
	exit;
}

sub write_alter_script {
	# Starting Printing
	printf("-- Converting Oracle SQL*Loader Control File %s to DB2\n\n", $filename);
	
	# Generate ALTER TABLE Scripts to generate DEFAULT statement for CONSTANT constrcuts
	if ($constant > 0)
	{
	    printf("-- ALTER Statements to take care of CONSTANT parameters\n");	
		for ( $c = 1 ; $c <= $constant ; $c++ ) {
	        $constant_list[$c][2] =~ s/"/'/g;        
		    printf("ALTER TABLE %s ALTER COLUMN %s SET WITH DEFAULT %s;\n", $table_name, $constant_list[$c][1], $constant_list[$c][2]);
		}
	    printf("\n\n");	
	} 	
}

sub write_load_script {
	# generate DB2 Load File
	printf("-- DB2 LOAD Script\n");	
	printf( "%s FROM %s\nOF %s\n", $type, $infile_name, $filetype );
	if ( $method eq 'L' ) {
		if ($timestampformat eq '') {
	       printf("MODIFIED BY ANYORDER USEDEFAULTS STRIPTBLANKS\n");		
		} else {
	       printf("MODIFIED BY ANYORDER USEDEFAULTS STRIPTBLANKS TIMESTAMPFORMAT=%s\n", $timestampformat);		
		}	
	    printf("DUMPFILE=\"%s\"\n", $dumpfile_name);
		printf( "METHOD %s \n(\n ", $method );
		for ( $c = 1 ; $c <= $column ; $c++ ) {
			if ( 1 < $c ) {
				printf("\n ,");
			}
			printf( "%s %s", $field_list[$c][2], $field_list[$c][3] );
		}
		printf("\n)\n");
		printf("MESSAGES \"%s\"\n", $messagefile_name);
	}
	if ( $method eq 'P' ) {
		if ($timestampformat eq '') {
	       printf("MODIFIED BY COLDEL0x%s ANYORDER USEDEFAULTS \n", $delimiter);		
		} else {
	       printf("MODIFIED BY COLDEL0x%s ANYORDER USEDEFAULTS TIMESTAMPFORMAT=%s\n", $delimiter, $timestampformat);		
		}	
	    printf("DUMPFILE=\"%s\"\n", $dumpfile_name);
		printf( "METHOD %s \n(\n", $method );
		for ( $c = 1 ; $c <= $column ; $c++ ) {
			if ( 1 < $c ) {
				printf(", ");
			}
			printf( "%d", $c );
		}
		printf("\n)\n");
		printf("MESSAGES \"%s\"\n", $messagefile_name);
	}
	printf( "%s INTO \"%s\".\"%s\" \n( ", $mode, uc($schema_name), uc($table_name) );
	for ( $c = 1 ; $c <= $column ; $c++ ) {
		if ( 1 < $c ) {
			printf("\n ,");
		}
		printf( "%s", $field_list[$c][1] );
	}
	printf("\n)\n");
	if ($mode eq 'REPLACE') {
	   printf("STATISTICS YES WITH DISTRIBUTION AND DETAILED INDEXES ALL\n");	
	}
	printf("NONRECOVERABLE\n");
	printf("INDEXING MODE AUTOSELECT\n");
	printf(";\n\n\n");	
}

sub write_defaultif_update {
	# Generate UPDATE statement for DEFAULTIF clause used in SQL*Loader scripts
	if ($defaultif > 0)
	{
	    printf("-- UPDATE Statements for setting proper DEFAULTIF parameters\n");
		for ( $c = 1 ; $c <= $defaultif ; $c++ ) {
		    printf("UPDATE \"%s\".\"%s\" \n", $schema_name, $table_name);
		    if ($defaultif_clause eq 'NULL') {
		        printf("       SET %s = NULL\n", $default_list[$c][1]);	    	
		    } else {
		        printf("       SET %s = DEFAULT\n", $default_list[$c][1]);		    	
		    }
			$default_list[$c][2] =~ s/"(.*)"/'\1'/g;
		    printf("WHERE  %s = %s;\n", $default_list[$c][1], $default_list[$c][2]);
		}
	    printf("\n\n");	
	}	
}

sub write_nullif_update {
	# Generate UPDATE statement for NULLIF clause used in SQL*Loader scripts
	if ($nullif > 0)
	{
	    printf("-- UPDATE Statements for setting proper NULLIF parameters\n");
		for ( $c = 1 ; $c <= $nullif ; $c++ ) {
			if ($nullif_list[$c][3] =~ /CHAR/) {
				$nullif_list[$c][2] =~ s/"(.*)"/\1/g;
			    printf("UPDATE \"%s\".\"%s\" \n", $schema_name, $table_name);
			    printf("       SET %s = NULL\n", $nullif_list[$c][1]);
			    if (uc($nullif_list[$c][2]) eq 'BLANKS') {
			        printf("WHERE  TRIM(%s) = '';\n", $nullif_list[$c][1]);	        	
			    } else {
			        printf("WHERE  %s = '%s';\n", $nullif_list[$c][1], $nullif_list[$c][2]);	        	
			    }
			}
		}
	    printf("\n\n");	
	}	
}

sub write_sql_update {
	# Write comments about double quoted SQL Statements that needs to be looked into
	if ($dqstring > 0)
	{
		my $sqlstring = '';
	    #printf("-- VERIFY these SQL Strings converted from SQL*Loader scripts\n");
		for ( $c = 1 ; $c <= $dqstring ; $c++ ) {
			$sqlstring = $dqstr_list[$c][2];
			if ($sqlstring =~ /^["]NVL/i) {
			   $sqlstring =~ s/NVL\(:/COALESCE(/ig;		
			   $sqlstring =~ s/^"//g;		
			   $sqlstring =~ s/"$//g;		
			   $sqlstring =~ s/(\d\d\d\d)-(\d\d)-(\d\d)/$1-$2-$3-00.00.00/g;	
			    printf("UPDATE \"%s\".\"%s\" \n", $schema_name, $table_name);
			    printf("       SET %s = %s;\n", $dqstr_list[$c][1], $sqlstring);
			} else {
			   if ($sqlstring =~ /^["]RTRIM/i) {
			      ; #printf("-- (RTRIM can be ignored - due to STRIPBLANKS) Table %s Column %s SQL String %s\n", $table_name, $dqstr_list[$c][1], $dqstr_list[$c][2]);					   			   	
			   } elsif ($sqlstring =~ /^["]LTRIM/i) {
			      ; #printf("-- (LTRIM can be ignored - due to STRIPBLANKS) Table %s Column %s SQL String %s\n", $table_name, $dqstr_list[$c][1], $dqstr_list[$c][2]);					   			   	
			   } else {
			      printf("-- (Manual conversion required) Table %s Column %s SQL String %s\n", $table_name, $dqstr_list[$c][1], $dqstr_list[$c][2]);					   	
			   }	
			}			
		}
	    printf("\n\n");		
	}
}

sub parse_cmd_arguments {
# read command parameters
	while ( $parameter = shift(@ARGV) ) {
		if ( "-c" eq $parameter ) {
			$filename = shift @ARGV;
		} 
		elsif ("-o" eq $parameter) {
	      $ostype = shift @ARGV;
	    }
		elsif ("-m" eq $parameter) {
	      $msg_dir = shift @ARGV;
	    }
		elsif ("-e" eq $parameter) {
	      $dump_dir = shift @ARGV;
	    }
		elsif ("-d" eq $parameter) {
	      $data_file_name = shift @ARGV;
	    }
		elsif ("-s" eq $parameter) {
	      $schema_name = uc(shift @ARGV);
	    }
		elsif ("-f" eq $parameter) {
	      $defaultif_clause = uc(shift @ARGV);
	    }
		elsif ("-g" eq $parameter) {
	      $timestampformat = shift @ARGV;
	      $timestampformat =~ s/^\"(.*?)\"$/$1/;
	      $timestampformat = '"' . $timestampformat . '"';
	    }
	}
	if ( '' eq $filename ) {
		&usage;
	}	
}

sub parse_commands {
# read array and parse commands
	$i = 1;
	while ( $i <= $max_line ) {
		if ( "INTO" eq uc( $array[$i] ) ) {
			&parse_into;
		}
		elsif ( "INFILE" eq uc( $array[$i] ) ) {
			&parse_infile;
		}
		elsif ( "INSERT" eq uc($array[$i]) || "APPEND" eq uc($array[$i]) || 
		        "REPLACE" eq uc($array[$i]) || "TRUNCATE" eq uc($array[$i])) {
			&set_mode(uc($array[$i]));
		}
		elsif ( "FIELDS" eq uc( $array[$i] ) ) {
			&parse_delimiter;
		}
		elsif ( "(" eq uc( $array[$i] ) ) {
			&parse_field_list;
		}
		$i++;
	}	
}

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# main
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

&parse_cmd_arguments;
&read_oractlfile($filename);
&parse_commands;

&write_alter_script;
&write_load_script;
&write_defaultif_update;
&write_nullif_update;
&write_sql_update;