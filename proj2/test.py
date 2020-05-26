import gym

env = gym.make("gym_zhed:zhed-v0")

print(env.observation_space)
print(env.observation_space.low)
print(env.observation_space.high)
print(env.observation_space.sample())

#for i in range(40):
"""
while not env.done:
    if env.hasMovesLeft():
        env.step(env.action_space.sample())
        env.render()
      
    else:
        env.reset()
"""