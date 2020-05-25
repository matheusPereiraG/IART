import gym
from gym import error, spaces, utils
from gym.utils import seeding
import numpy as np
import matplotlib.pyplot as plt
import math 

class ZhedEnv(gym.Env):
  metadata = {'render.modes': ['human']}

  def __init__(self, filename):
    self.board = np.genfromtxt(filename, dtype =str)
    self.r = len(self.board)
    self.c = len(self.board[1,:])
    self.done = False
    self.pieces = self.getpieces()
    print(self.pieces)
    self.action_space = spaces.Discrete(4*len(self.pieces))
    self.possible_moves = self.action_space
    self.observation_space = spaces.Discrete(4^len(self.pieces))
    #self.addPieces()

  def getpieces(self):
    pieces = np.array([[0,0,0]])
    for i in range(0, self.r):
      for j in range(0, self.c):
          if self.board[i,j] != '.' and self.board[i,j] != 'W':
            piece = [[self.board[i,j], i, j]]
            pieces = np.append(pieces,piece, axis=0)
    pieces = np.delete(pieces,0,0)
    return pieces


  def step(self, action):
    pieceIndex = action // 4
    pieceMove = action % 4
    print(pieceIndex)
    print(pieceMove)

  def reset(self):
    self.board = np.full((self.r,self.c),'*')

  def render(self, mode='human'):
    print(np.matrix(self.board))

  def close(self):
    ...

  def addPieces(self):
    ...
  
  def calculateActionSpace(self):
    pass
    

if __name__== "__main__":
  env = ZhedEnv("level1.txt")
  env.render()
  env.step(env.action_space.sample())