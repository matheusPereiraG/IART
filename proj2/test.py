import gym

env = gym.make("gym_zhed:zhed-v0")



#for i in range(40):
while not env.done:
    if env.hasMovesLeft():
        env.step(env.action_space.sample())
        env.render()
      
    else:
        env.reset()
