import gym
from gym import error, spaces, utils
from gym.utils import seeding
import numpy as np
import matplotlib.pyplot as plt

class ZhedEnv(gym.Env):
  metadata = {'render.modes': ['human']}

  def __init__(self, m, n, pieces, terminalState):
    self.board = np.zeros((m,n))
    self.m = m
    self.n = n
    self.done = False
    self.pieces = pieces
    #for each piece 4 different movements thus being a multidiscrete space
    aux_array = np.full(len(pieces), 4)
    self.action_space = spaces.MultiDiscrete(aux_array)
    print(self.action_space.sample())
    self.observation_space
    self.addPieces()
    #self.action_space

  def step(self, action):
    ...
  def reset(self):
    ...
  def render(self, mode='human'):
    ...
  def close(self):
    ...

  def addPieces(self):
    ...
  
  def calculateActionSpace(self):
    pass
    

if __name__== "__main__":
  pieces = [[2,2,4], [2,4,5]] #level 4 of original zhed game
  ZhedEnv(8,8,pieces,[4,2])