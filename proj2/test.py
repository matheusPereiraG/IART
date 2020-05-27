import gym

import numpy as np
from IPython.display import clear_output
from time import sleep
import random


def print_frames(frames):
    for i, frame in enumerate(frames):
        clear_output(wait=True)
        # print(frame['frame'].getvalue())
        print(f"Timestep: {i + 1}")
        print(f"State: {frame['state']}")
        print(f"Action: {frame['action']}")
        print(f"Reward: {frame['reward']}")
        sleep(.1)


env = gym.make("gym_zhed:zhed-v0")
print(env.observation_space.n)
q_table = np.zeros([env.observation_space.n, env.action_space.n])
print(q_table)

#q_table = np.zeros([env.board.tobytes(), env.action_space.n])



#print("state: ", state)


# Hyperparameters
alpha = 0.1
gamma = 0.6
epsilon = 0.1


frames = []

### training agent
for i in range(1, 1001):

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



print(np.argmax(q_table[4391040]))

#print_frames(frames)

### Test q_table

total_epochs, total_penalties = 0, 0
episodes = 100

for _ in range(episodes):
    epochs, penalties, reward = 0, 0, 0
    
    done = False
    
    while not done:
        state = env.encode()
        action = np.argmax(q_table[state])
        state, reward, done, info = env.step(action)

        if reward < 0:
            penalties += reward/10

        epochs += 1

        if not env.hasMovesLeft():
            env.reset()
    
    total_penalties += penalties
    total_epochs += epochs

print(f"Results after {episodes} episodes:")
print(f"Average timesteps per episode: {total_epochs / episodes}")
print(f"Average penalties per episode: {total_penalties / episodes}")
