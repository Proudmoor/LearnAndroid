s#coding=utf-8
import urllib
import json

url = 'https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=809DF5B077AC15B512C4ECFA836470B0&account_id=76561198098649471'


urllib.urlretrieve(url, 'match.json')