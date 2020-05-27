import gym

import numpy as np
from IPython.display import clear_output
from time import sleep
import random


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

filestr = "level"
level_number = input("Enter Level : ")
filestr += level_number
filestr += ".txt"

env = gym.make("gym_zhed:zhed-v0", filename=filestr)

q_table_file = "q_table"
q_table_file += level_number
q_table_file += ".txt"
try: q_table = np.genfromtxt(q_table_file, dtype=float)
except OSError: q_table = np.zeros([env.observation_space.n, env.action_space.n])


# Hyperparameters
alpha = 0.1
gamma = 0.6
epsilon = 0.1


frames = []

### training agent
for i in range(1, 50):

    steps, penalties, reward = 0, 0, 0
    done = False
    while not done:
        state = env.encode()
        
        if random.uniform(0,1) < epsilon:
            action = env.action_space.sample() #Explore
        else: 
            action = np.argmax(q_table[state]) #Exploit

        next_state, reward, done, info = env.step(action)

        old_value = q_table[state,action]
        next_max = np.max(q_table[next_state])

        new_value = (1 - alpha) * old_value + alpha * (reward + gamma * next_max)

        q_table[state, action] = new_value

        if reward < 0:
            penalties += reward/10

        frames.append({
            'frame': env.render(),
            'state': state,
            'action': action,
            'reward': reward
        })

        steps += 1

        if not env.hasMovesLeft():
            env.reset()

print("Timesteps taken: {}".format(steps))
print("Penalties incurred: {}".format(penalties))

print_frames(frames)
