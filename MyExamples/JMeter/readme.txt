Instructions:

TCP Endpoint in Listen mode. Where Start of message is Hex 02 and end of message is Hex 03.

Configurations you can do in script –

1)	Modify <stringProp name="LoopController.loops">20</stringProp> - How many times do you want to run the script.
2)	Modify <stringProp name="ThreadGroup.num_threads">5</stringProp> - How many threads you want to post message on TCP port concurrently.
3)	Modify <stringProp name="TCPSampler.server">prodlab-scope-secondary.us.manh.com</stringProp> - Host where TCP endpoint is running.
4)	Modify <stringProp name="TCPSampler.port">9003</stringProp> - Port on which TCP endpoint is listening.
5)	Replace {Message} with actual message in tag <stringProp name="TCPSampler.request"> &#x2;{Message}&#x3; </stringProp>
6)	Modify <stringProp name="filename">C:\Data.txt</stringProp>  - Replace with your file path.

Execute jmx script on console as –

                /u01/tools/apache-jmeter-2.11/bin/jmeter.sh -n -t TCP_Sampler.jmx
