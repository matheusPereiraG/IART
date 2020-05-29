import gym
import sys

import numpy as np
from IPython.display import clear_output
from time import sleep
import random
import os
import matplotlib.pyplot as plt

if(len(sys.argv) != 3):
    print("Usage: python3 train_q_learning.py <level_number> <number_of_practices>")
    exit()

filestr = "level"
level_number = sys.argv[1] #input("Enter Level : ")
filestr += level_number
filestr += ".txt"
filepath = "levels/" + filestr

train_number = int(sys.argv[2]) #int(input("Enter number of episodes to train: "))

env = gym.make("gym_zhed:zhed-v0", filename=filepath)

q_table_file = "q_table"
q_table_file += level_number
q_table_file += ".txt"
q_table_path = "./tables/Qlearning/"

if not os.path.exists(q_table_path):
    os.makedirs(q_table_path)

q_table_path += q_table_file

print("start generating Q_table.")
try: q_table = np.genfromtxt(q_table_path)
except OSError: q_table = np.zeros([env.observation_space.n, env.action_space.n+1])
print("Q_table fully generated.")

# Hyperparameters
alpha = 0.8     # learning rate (determina a importancia do reward atual)
gamma = 1     # discount factor (determina a importancia de futuras rewards)
epsilon = 0.2   # explore rate (determina se explora ações "piores" mais vezes) 


frames = []

total_steps = 0
total_penalties = 0
total_solutions_found = 0

### training agent
for i in range(1, train_number):

    steps, penalties, reward = 0, 0, 0
    done = False
    while not done:
        
        print()
        print(env.render())
        
        steps += 1
        total_steps += 1
        
        state = env.encode()
        
        if random.uniform(0,1) < epsilon:
            action = env.action_space.sample() #Explore
        else: 
            selectedEntry = []
            for entry in q_table:
                if entry[0] == 0:
                    break
                if(entry[0] == state):
                    selectedEntry = entry
                    break
            if len(selectedEntry) == 0:
                for entry in q_table:
                    if(entry[0] == 0):
                        entry[0] = state
                        selectedEntry = entry
                        break
            entry = np.delete(selectedEntry,0,0)
            action = np.argmax(entry) #Exploit
        

        next_state, reward, done, info = env.step(action)

        selectedEntry = []
        for entry in q_table:
            if entry[0] == 0:
                break
            if(entry[0] == state):
                selectedEntry = entry
                break
        if len(selectedEntry) == 0:
            for entry in q_table:
                if entry[0] == 0:
                    entry[0] = state
                    selectedEntry = entry
                    break

        old_value = selectedEntry[action+1]

        selectedNextEntry = []
        for entry in q_table:
            if entry[0] == 0:
                break
            if(entry[0] == next_state):
                selectedNextEntry = entry
                break
        if len(selectedNextEntry) == 0:
            for entry in q_table:
                if entry[0] == 0:
                    entry[0] = next_state
                    selectedNextEntry = entry
                    break
        
        entry = np.delete(selectedNextEntry,0,0)
        next_max = np.max(entry)

        new_value = (1 - alpha) * old_value + alpha * (reward + gamma * next_max)

        for entry in q_table:
            if entry[0] == state:
                entry[action+1] = new_value
                break

        if reward < 0:
            penalties += 1
            total_penalties += 1

        
        print(f"Timestep: {steps}")
        print(f"State: {state}")
        print(f"Action: {action}")
        print(f"Reward: {reward}")
        print(f"Episode: {i}")
        
        if done:
            total_solutions_found += 1;

        if not env.hasMovesLeft():
            env.reset()
            done = True


print("\n\n")
print(f"Results after {train_number} train sections:")
print(f"Total timesteps: {total_steps}")
print(f"Total penalties: {total_penalties}")
print(f"Total solutions found: {total_solutions_found}")
print(f"Average timesteps per episode: {total_steps / train_number}")
print(f"Average penalties per episode: {total_penalties / train_number}")
print(f"Percentage of solutions found: {total_solutions_found / train_number}")

np.savetxt(q_table_path,q_table)
