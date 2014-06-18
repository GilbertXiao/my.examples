#!/bin/bash
echo nmon before = `pidof -c ./nmon_x86_64_rhel6`
./nmon_x86_64_rhel6 -f -r -t -s 5 -c 300000000
echo nmon after = `pidof -c ./nmon_x86_64_rhel6`

