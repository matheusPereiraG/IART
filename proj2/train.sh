#!/bin/bash


numLevels=$1


if [ $# -ne 1 ]; then
    echo "Usage: $0 <number of levels>"
    exit 1
fi

for ((i = 1 ; i <= $numLevels ; i++)); do
    konsole --separate --hold -e "python3 train_SARSA.py $i 10000" &
done

for ((i = 1 ; i <= $numLevels ; i++)); do
    konsole --separate --hold -e "python3 train_q_learning.py $i 10000" &
done
