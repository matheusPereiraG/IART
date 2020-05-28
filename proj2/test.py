import gym

import numpy as np
from IPython.display import clear_output
from time import sleep
import random
import os


def print_frames(frames):
    for i, frame in enumerate(frames):
        clear_output(wait=True)
        print()
        print(frame['frame'])
        print(f"Timestep: {i + 1}")
        print(f"State: {frame['state']}")
        print(f"Action: {frame['action']}")
        print(f"Reward: {frame['reward']}")
        sleep(.1)

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

episodes = int(input("Enter number of episodes: "))

env = gym.make("gym_zhed:zhed-v0", filename=filepath)

q_table_file = "q_table"
q_table_file += level_number
q_table_file += ".txt"
q_table_path += q_table_file
try: q_table = np.genfromtxt(q_table_path, dtype=float)
except OSError:
    print("Q table does not exist, please train your agent first")
    exit()


### Test q_table

total_epochs, total_penalties = 0, 0

for _ in range(episodes):
    epochs, penalties, reward = 0, 0, 0
    
    endEpisode = False
    
    while not endEpisode:
        state = env.encode()
        current_entry = []
        for entry in q_table:
            if entry[0] == state:
                current_entry = np.delete(entry,0,0)
                break
        action = np.argmax(current_entry)
        state, reward, done, info = env.step(action)

        if reward < 0:
            penalties += reward/10

        epochs += 1

        if done:
            print("Found solution")
            print(env.render())
            env.reset()
            endEpisode = True

        if not env.hasMovesLeft():
            env.reset()
            endEpisode = True
    
    total_penalties += penalties
    total_epochs += epochs

print(f"Results after {episodes} episodes:")
print(f"Total timesteps: {total_epochs}")
print(f"Total penalties: {total_penalties}")
print(f"Average timesteps per episode: {total_epochs / episodes}")
print(f"Average penalties per episode: {total_penalties / episodes}")