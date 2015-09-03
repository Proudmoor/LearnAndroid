#! /usr/bin/python
#coding=utf-8
import json
import urllib

url = 'https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=809DF5B077AC15B512C4ECFA836470B0&language=zh'
page = urllib.urlopen(url)
urllib.urlretrieve(url, 'heroes.json')
