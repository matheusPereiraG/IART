import gym
from gym import error, spaces, utils
from gym.utils import seeding
import numpy as np
from numpy import random
import matplotlib.pyplot as plt
import math 
import os
import random
from colorama import Fore, Back, Style

class ZhedEnv(gym.Env):
  metadata = {'render.modes': ['human']}

  def __init__(self, filename):
    super().__init__()
    path = os.getcwd()
    os.path.abspath(os.path.join(path, filename))
    self.filename = filename
    self.board = np.genfromtxt(self.filename, dtype =str)
    self.r = len(self.board)
    self.c = len(self.board[1,:])
    self.done = False
    self.pieces = self.getpieces()
    self.action_space = spaces.Discrete(len(self.pieces) *4) #[0,1,2,3,4,5,6,7]
    self.possible_moves = np.full(self.action_space.n,True)
    self.observation_space = spaces.Discrete(math.factorial(len(self.pieces)) * pow(4,len(self.pieces))) #n!*4^n


  def step(self, action): #0-UP, 1-DOWN, 2-RIGHT, 3-LEFT
    
    # verifica se a acao é possivel
    if self.possible_moves[action] == False:
      return self.encode(), -100, False, {}

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

    if(self.done):
      reward = 100
    else:
      reward = diffExpansion * 10

    return self.encode(), reward, self.done, {}

  def reset(self):
    self.__init__(self.filename)

  def render(self, mode='human', close=False):
    printString = ""
    for i in range(self.r):
      for j in range(self.c):
        if self.board[i,j] == '.':
          printString += f"{Fore.YELLOW}\u25A0{Style.BRIGHT} "
        elif self.board[i,j] == '#':
          printString += f"{Fore.RED}\u25A0{Style.BRIGHT} "
        else:
          printString += f"{Fore.RED}{self.board[i,j]} "
      printString += f"\n{Style.RESET_ALL}"
    return printString

  def close(self):
    ...

  def getPossibleRandomAction(self):
    pass


  def encode(self):
    code = 0
    num = 0
    for i in range(self.r):
      for j in range(self.c):
        if(self.board[i,j] == '.'):
          temp = 0
        else:
          temp = 1
        code = code | (temp << num)
        num += 1
    return code
  
    
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
