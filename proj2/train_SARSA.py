import gym
import sys

import numpy as np
from IPython.display import clear_output
from time import sleep
import random
import os


def pick_action(state):
    if random.uniform(0, 1) < epsilon:
        action = env.action_space.sample()  # Explore
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
        entry = np.delete(selectedEntry, 0, 0)
        action = np.argmax(entry)  # Exploit
    return action


def get_entry(state):
    selectedEntry = []
    for entry in q_table:
        if(entry[0] == state):
            selectedEntry = entry
            return selectedEntry
    if len(selectedEntry) == 0:
        for entry in q_table:
            if entry[0] == 0:
                entry[0] = state
                selectedEntry = entry
                return selectedEntry

def save_entry(state, action, value):
    for entry in q_table:
        if entry[0] == state:
            entry[action+1] = value
            break
        
if(len(sys.argv) != 3):
    print("Usage: python3 train_SARSA.py <level_number> <number_of_practices>")
    exit()


filestr = "level"
level_number = sys.argv[1] #input("Enter Level : ")
filestr += level_number
filestr += ".txt"
filepath = "levels/" + filestr

train_number = int(sys.argv[2]) #int(input("Enter number of episodes to train: "))

env = gym.make("gym_zhed:zhed-v0", filename=filepath)

sarsa_table_file = "q_table"
sarsa_table_file += level_number
sarsa_table_file += ".txt"
sarsa_table_path = "./tables/SARSA/"

if not os.path.exists(sarsa_table_path):
    os.makedirs(sarsa_table_path)

sarsa_table_path += sarsa_table_file

print("start generating Sarsa table.")
try:
    q_table = np.genfromtxt(sarsa_table_path)
except OSError:
    q_table = np.zeros([env.observation_space.n, env.action_space.n+1])
print("Sarsa fully generated.")

# Hyperparameters
alpha = 0.8     # learning rate (determina a importancia do reward atual)
gamma = 1     # discount factor (determina a importancia de futuras rewards)
epsilon = 0.1   # explore rate (determina se explora ações "piores" mais vezes)


frames = []

total_steps = 0
total_penalties = 0
total_solutions_found = 0

# training agent
for i in range(1, train_number):

    steps, penalties, reward = 0, 0, 0
    env.reset()
    s = env.encode()
    a = pick_action(s)
    endEpisode = False
    while not endEpisode:
        
        print()
        print(env.render())
        print(f"Episode: {i}")
        print(f"Timestep: {steps}")
        print(f"State: {s}")
        print(f"Action: {a}")
        print(f"Reward: {reward}")

        # do first action and get next state
        s_, reward, done, info = env.step(a)
        steps += 1
        total_steps += 1

        # go get q-values for that state, if does not exist get an entry with values to fill (where state == 0)
        selectedEntry = get_entry(s)

        old_value = selectedEntry[a+1]

        # get entry for next state if does not exist create one
        selectedNextEntry = get_entry(s_)
        
        entry = np.delete(selectedNextEntry, 0, 0)
        new_value = old_value
        if done:
            new_value += alpha * (reward - old_value)
            total_solutions_found += 1
            endEpisode = True
            save_entry(s, a, new_value)
            print()
            print(env.render())
            print(f"Episode: {i+1}")
            print(f"Timestep: {steps}")
            print(f"State: {s}")
            print(f"Action: {a}")
            print(f"Reward: {reward}")
            break
        else:  # perform next action
            a_ = pick_action(s_)
            value_next_state_action = entry[a_]
            new_value += alpha * (reward + (gamma * value_next_state_action) - old_value)
            save_entry(s, a, new_value)

        s, a = s_, a_

        if reward < 0:
            penalties += 1
            total_penalties += 1
        
        if not env.hasMovesLeft():
            endEpisode = True
            
    print()
    print(env.render())
    print(f"Episode: {i}")
    print(f"Timestep: {steps}")
    print(f"State: {s}")
    print(f"Action: {a}")
    print(f"Reward: {reward}")


print("\n\n")
print(f"Results after {train_number} train sections:")
print(f"Total timesteps: {total_steps}")
print(f"Total penalties: {total_penalties}")
print(f"Total solutions found: {total_solutions_found}")
print(f"Average timesteps per episode: {total_steps / train_number}")
print(f"Average penalties per episode: {total_penalties / train_number}")
print(f"Percentage of solutions found: {total_solutions_found / train_number}")


np.savetxt(sarsa_table_path, q_table)
