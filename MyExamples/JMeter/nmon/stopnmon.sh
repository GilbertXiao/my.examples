#!/bin/bash
echo nmon before = `pidof -c nmon_x86_64_rhel6`
kill -SIGUSR2 `pidof -c nmon_x86_64_rhel6`
echo nmon after = `pidof -c nmon_x86_64_rhel6`

