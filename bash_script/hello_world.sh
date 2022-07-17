#!/bin/bash

middle=$(grep startActivity log.txt)
#echo $middle

get_method_time() {
  result=$(grep $1 log.txt | awk '{print $2}')
  return "$result"
}

BAI_WAN=1000000

start_activity_time=$(grep startActivity log.txt | awk '{print $1,$2}')
create_time=$(grep onCreate log.txt | awk '{print $1,$2}')
resume_time=$(grep onResume log.txt | awk '{print $1,$2}')

print_method_time() {
  echo "start_activity_time = $start_activity_time"
  echo "create_time         = $create_time"
  echo "resume_time         = $resume_time"

  echo "=============================================="
}

print_method_time

start_activity_time_stamp=$(date +%s%N -d "$start_activity_time")
create_time_stamp=$(date +%s%N -d "$create_time")
resume_time_stamp=$(date +%s%N -d "$resume_time")

print_method_timestamp() {
  echo "start_activity_time_stamp =  $(expr $start_activity_time_stamp / $BAI_WAN)"
  echo "create_time_stamp         =  $(expr $create_time_stamp / $BAI_WAN)"
  echo "resume_time_stamp         =  $(expr $resume_time_stamp / $BAI_WAN)"

  echo "=============================================="
}

print_method_timestamp

create_duration=$(($(date +%s%N -d "$create_time") / $BAI_WAN - $(date +%s%N -d "$start_activity_time") / $BAI_WAN))
resume_duration=$(($(date +%s%N -d "$resume_time") / $BAI_WAN - $(date +%s%N -d "$start_activity_time") / $BAI_WAN))
echo "create_duration = $create_duration ms"
echo "resume_duration = $resume_duration ms"
