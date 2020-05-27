from gym.envs.registration import register

register(
    id='zhed-v0',
    entry_point='gym_zhed.envs:ZhedEnv'
    #kwargs={'filename': "level1.txt"}
)