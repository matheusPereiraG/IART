import gym

import numpy as np
from IPython.display import clear_output
from time import sleep
import random
import os

train_number = 10

def pick_action():
    if random.uniform(0, 1) < epsilon:
        action = env.action_space.sample()  # Explore
    else:
        selectedEntry = []
        for entry in q_table:
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


filestr = "level"
level_number = input("Enter Level : ")
filestr += level_number
filestr += ".txt"

env = gym.make("gym_zhed:zhed-v0", filename=filestr)

sarsa_table_file = "sarsa_table"
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
alpha = 0.1     # learning rate (determina a importancia do reward atual)
gamma = 0.6     # discount factor (determina a importancia de futuras rewards)
epsilon = 0.2   # explore rate (determina se explora ações "piores" mais vezes)


frames = []

total_steps = 0
total_penalties = 0

# training agent
for i in range(1, train_number):

    steps, penalties, reward = 0, 0, 0
    env.reset()
    s = env.encode()
    a = pick_action()
    done = False
    while not done:
        
        # do first action and get next state
        s_, reward, done, info = env.step(a)

        #go get q-values for that state, if does not exist get an entry with values to fill (where state == 0)
        selectedEntry = []
        for entry in q_table:
            if(entry[0] == s):
                selectedEntry = entry
                break
        if len(selectedEntry) == 0:
            for entry in q_table:
                if entry[0] == 0:
                    entry[0] = s
                    selectedEntry = entry
                    break

        old_value = selectedEntry[a+1]

        #perform next action
        
        selectedNextEntry = []
        for entry in q_table:
            if(entry[0] == s_):
                selectedNextEntry = entry
                break
        if len(selectedNextEntry) == 0:
            for entry in q_table:
                if entry[0] == 0:
                    entry[0] = s_
                    selectedNextEntry = entry
                    break
        entry = np.delete(selectedNextEntry, 0, 0)
        """
        if done: 
            new_value += alpha * (reward - old_value)
        else:
            new_value += alpha * (reward + (gamma * ))
        """
        next_max = np.max(entry)

        new_value = (1 - alpha) * old_value + alpha * \
            (reward + gamma * next_max)

        for entry in q_table:
            if entry[0] == state:
                entry[action+1] = new_value
        #q_table[state, action] = new_value

        if reward < 0:
            penalties += reward
            total_penalties += reward

        print()
        print(env.render())
        print(f"Timestep: {steps}")
        print(f"State: {state}")
        print(f"Action: {action}")
        print(f"Reward: {reward}")

        """
        frames.append({
            'frame': env.render(),
            'state': state,
            'action': action,
            'reward': reward
        })
        """

        steps += 1
        total_steps += 1

        if not env.hasMovesLeft():
            done = True

print("Timesteps taken: {}".format(steps))
print("Penalties incurred: {}".format(penalties))

print(f"Results after {train_number} train sections:")
print(f"Total timesteps: {total_steps}")
print(f"Total penalties: {total_penalties}")
print(f"Average timesteps per episode: {total_steps / train_number}")
print(f"Average penalties per episode: {total_penalties / train_number}")


np.savetxt(sarsa_table_path, q_table)



