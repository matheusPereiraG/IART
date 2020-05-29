import gym

import numpy as np
from IPython.display import clear_output
from time import sleep
import random
import os


algorithm = input("Enter algorithm to test: \n 1: SARSA \n 2: QLearning \n")

if(int(algorithm) == 1):
     q_table_path = "./tables/SARSA/"
elif(int(algorithm) == 2):
    q_table_path = "./tables/Qlearning/"
else: 
    print("Invalid input")
    exit()

filestr = "level"
level_number = input("Enter Level: ")
filestr += level_number
filestr += ".txt"
filepath = "levels/" +filestr

env = gym.make("gym_zhed:zhed-v0", filename=filepath)

q_table_file = "q_table"
q_table_file += level_number
q_table_file += ".txt"
q_table_path += q_table_file
print("Getting Q_table...")
try: q_table = np.genfromtxt(q_table_path, dtype=float)
except OSError:
    print("Q table does not exist, please train your agent first")
    exit()
print("Q table loaded")

### Test q_table


epochs, penalties, reward = 0, 0, 0

done = False

while not done:
    print()
    print("Move: ", epochs)
    print(env.render())
    state = env.encode()
    current_entry = []
    for entry in q_table:
        if entry[0] == state:
            current_entry = np.delete(entry,0,0)
            break
    action = np.argmax(current_entry)
    state, reward, done, info = env.step(action)

    if reward < 0:
        penalties += 1

    epochs += 1

    if done:
        print()
        print("Move: ", epochs)
        print(env.render())
        print("Congratulations! Your agent found the solution.")

    elif not env.hasMovesLeft():
        print()
        print("Move: ", epochs)
        print(env.render())
        print("Your agent is dumber than you. Practice more.")
        done = True
