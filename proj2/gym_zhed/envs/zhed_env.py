import gym
from gym import error, spaces, utils
from gym.utils import seeding
import numpy as np
import matplotlib.pyplot as plt

class ZhedEnv(gym.Env):
  metadata = {'render.modes': ['human']}

  def __init__(self, m, n, pieces, terminalState):
    self.grid = np.zeros((m,n))
    self.m = m
    self.n = n

  def step(self, action):
    ...
  def reset(self):
    ...
  def render(self, mode='human'):
    ...
  def close(self):
    ...

if __name__== "__main__":
  ZhedEnv(10,10,[[1,2,3], [1,4,4]], [10,10])
  print("bacalhau")