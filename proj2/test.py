import gym
import numpy as np 
from IPython.display import clear_output
from time import sleep

def print_frames(frames):
    for i, frame in enumerate(frames):
        clear_output(wait=True)
        #print(frame['frame'].getvalue())
        print(f"Timestep: {i + 1}")
        print(f"State: {frame['state']}")
        print(f"Action: {frame['action']}")
        print(f"Reward: {frame['reward']}")
        sleep(.1)


env = gym.make("gym_zhed:zhed-v0")
print(env.observation_space.n)
q_table = np.zeros([env.observation_space.n, env.action_space.n])

print("q_table:")
print(q_table)


state = env.encode()
print("state: ", state)

steps = 0
penalties, reward = 0,0

frames = []

done = False


while not done:
    action = env.action_space.sample()
    state, reward, done, info = env.step(action)

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

