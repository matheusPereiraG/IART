import gym
from gym import error, spaces, utils
from gym.utils import seeding
import numpy as np
import matplotlib.pyplot as plt
import math 
import os

class ZhedEnv(gym.Env):
  metadata = {'render.modes': ['human']}

  def __init__(self, filename):
    path = os.getcwd()
    os.path.abspath(os.path.join(path, filename))
    self.filename = filename
    self.board = np.genfromtxt(self.filename, dtype =str)
    self.r = len(self.board)
    self.c = len(self.board[1,:])
    self.done = False
    self.pieces = self.getpieces()
    print(self.pieces)
    self.action_space = spaces.Discrete(len(self.pieces) *4) #[0,1,2,3,4,5,6,7]
    self.possible_moves = np.full(self.action_space.n,True)
    print(self.possible_moves)
    self.observation_space = spaces.Box(0, 1, [self.r,self.c],dtype=np.int) #spaces.Box(0,len(self.pieces))
    self.observation_space.sample()


  def step(self, action): #0-UP, 1-DOWN, 2-RIGHT, 3-LEFT
    
    # verifica se a acao é possivel
    if self.possible_moves[action] == False:
      print("impossible action ", action)
      return #state, reward, done, {}

    # obtem a peça e a direção pretendidos
    pieceIndex = action // 4
    pieceMove = action % 4
    piece = self.pieces[pieceIndex]

    # realiza a jogada
    diffExpansion = self.moveSwitcher(pieceMove, piece)

    # atualiza os valores de ações possiveis
    self.pieces[pieceIndex] = [piece[0], piece[1], piece[2], True]
    self.possible_moves[pieceIndex*4] = False
    self.possible_moves[pieceIndex*4+1] = False
    self.possible_moves[pieceIndex*4+2] = False
    self.possible_moves[pieceIndex*4+3] = False


    print(self.possible_moves)
    print(self.pieces)
    print("diffExpansion: ", diffExpansion)

    #return observation, reward, done, {}

  def reset(self):
    self.__init__(self.filename)

  def render(self, mode='human'):
    print(np.matrix(self.board))

  def close(self):
    ...

  def addPieces(self):
    ...
  
  def calculateActionSpace(self):
    ...
    
  def getpieces(self):
    pieces = np.array([[0,0,0,False]])
    for i in range(0, self.r):
      for j in range(0, self.c):
          if self.board[i,j] != '.' and self.board[i,j] != 'W':
            piece = [[int(self.board[i,j]), int(i), int(j), False]]
            pieces = np.append(pieces,piece, axis=0)
    pieces = np.delete(pieces,0,0)
    return pieces

  def moveSwitcher(self,argument,piece):
    switcher = {
      0: self.down,
      1: self.up,
      2: self.right,
      3: self.left
    }
    func = switcher.get(argument)
    return func(piece)
  
  def down(self,piece):
    print("down")
    numCellsExpanded = 0
    x = int(piece[2])
    y = int(piece[1])
    power = int(piece[0])
    counter = power
    self.board[y,x] = '#'
    i = 1
    while i <= counter:
      if y + i < self.r:
        if self.board[y+i,x] == '.':
          self.board[y+i,x] = '#'
          numCellsExpanded += 1
        elif self.board[y+i,x] == 'W':
          self.board[y+i,x] = '#'
          numCellsExpanded += 1
          self.done = True
          return numCellsExpanded - power
        else: 
          numCellsExpanded += 1
          counter +=1
      i+=1
    return numCellsExpanded - power

  def up(self,piece):
    print("up")
    numCellsExpanded = 0
    x = int(piece[2])
    y = int(piece[1])
    power = int(piece[0])
    counter = power
    self.board[y,x] = '#'
    i = 1
    while i <= counter:
      if y - i >= 0:
        if self.board[y-i,x] == '.':
          self.board[y-i,x] = '#'
          numCellsExpanded += 1
        elif self.board[y-i,x] == 'W':
          self.board[y-i,x] = '#'
          numCellsExpanded += 1
          self.done = True
          return numCellsExpanded - power
        else: 
          numCellsExpanded += 1
          counter += 1
      i+=1
    return numCellsExpanded - power

  def right(self,piece):
    print("right")
    numCellsExpanded = 0
    x = int(piece[2])
    y = int(piece[1])
    power = int(piece[0])
    counter = power
    self.board[y,x] = '#'
    i = 1
    while i <= counter:
      if x + i < self.c:
        if self.board[y,x +i] == '.':
          self.board[y,x +i] = '#'
          numCellsExpanded += 1
        elif self.board[y,x +i] == 'W':
          self.board[y,x+i] = '#'
          numCellsExpanded += 1
          self.done = True
          return numCellsExpanded - power
        else: 
          numCellsExpanded += 1
          counter += 1
      i+=1
    return numCellsExpanded - power

  def left(self,piece):
    print("left")
    numCellsExpanded = 0
    x = int(piece[2])
    y = int(piece[1])
    power = int(piece[0])
    counter = power
    self.board[y,x] = '#'
    i = 1
    while i <= counter:
      if x - i >= 0:
        if self.board[y,x-i] == '.':
          self.board[y,x-i] = '#'
          numCellsExpanded += 1
        elif self.board[y,x-i] == 'W':
          self.board[y,x-i] = '#'
          numCellsExpanded += 1
          self.done = True
          return numCellsExpanded - power
        else: 
          numCellsExpanded += 1
          counter += 1
      i+=1
    return numCellsExpanded - power

  def hasMovesLeft(self):
    for a in self.possible_moves:
      if a:
        return True
    return False



"""
if __name__== "__main__":
  env = ZhedEnv("level1.txt")
  env.render()
  while not env.done:
    if len(env.pieces) == 0:
      env.reset()
    else:
      env.step(env.action_space.sample())
      env.render()
"""  