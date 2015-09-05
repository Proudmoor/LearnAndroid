#coding=utf-8
import urllib
import json
import time

f = file('match.json')
hf = file('heroes.json')

s = json.load(f)
herojson = json.load(hf)
heroes = herojson['result']['heroes']
heroes.sort(lambda x, y: cmp(x['id'],y['id']))

print heroes[100]
num =[]
for i in range(1,113):
	num.append(i)
ret=[]

for i in heroes:
	ret.append(i['id'])
print ret

diff = [x for x in num if x not in ret]
print diff
for i in s['result']['matches']:
	id = i['match_id']
	url = 'https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=809DF5B077AC15B512C4ECFA836470B0&match_id=%d' %id 
	page = urllib.urlopen(url)
	s = json.loads(page.read())

	hero = 0
	print 'match    id: %d' %id
	if s['result']['radiant_win']:
		print 'radiant win: true'
	else:
		print 'radiant win: false'

	print 'start  time: %s'   %time.asctime(time.localtime(i['start_time']))
	duration = s['result']['duration']
	h = duration /60
	m  = duration %60
	print 'last  time: %s:%s'  %(h,m)
	for j in i['players']:
		if j['account_id'] == 138383743:
			heroId = j['hero_id'] - 1
			heroSlot = j['player_slot']
			if s['result']['radiant_win'] and heroSlot < 5:
				print 'match  result: 胜利'
			else:
				print 'match  result: 失败'
			if heroId < 24:
				heroId = heroId;
			elif heroId >24 and heroId < 108:
				heroId = heroId - 1;
			else:
				heroId = heroId - 2;

			print 'hero   played: %s' %heroes[heroId]['localized_name'].encode('utf8')
	
	print '-------------------------------------------'
