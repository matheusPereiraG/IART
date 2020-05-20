import gym
env = gym.make('CartPole-v0')
print(env.action_space)
#> Discrete(2)
print(env.observation_space.high)
print(env.observation_space.low)
#> Box(4,)