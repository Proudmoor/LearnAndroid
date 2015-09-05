#coding=utf-8

import urllib
import json

herofile = file('heroes.json')

herojson = json.load(herofile)

heroes = herojson['result']['heroes']

for i in heroes:
	heroName = i['name']
	url_full = 'http://cdn.dota2.com/apps/dota2/images/heroes/'+heroName.replace('npc_dota_hero_', '') + '_full.png' 
	url_vert = 'http://cdn.dota2.com/apps/dota2/images/heroes/'+heroName.replace('npc_dota_hero_', '') + '_vert.jpg'
	url_sb = 'http://cdn.dota2.com/apps/dota2/images/heroes/'+heroName.replace('npc_dota_hero_', '') + '_sb.png'
	url_lg = 'http://cdn.dota2.com/apps/dota2/images/heroes/'+heroName.replace('npc_dota_hero_', '') + '_lg.png'
	print url_full
	urllib.urlretrieve(url_full, 'heroImage/fullImage/%d.png' %i['id'])
	urllib.urlretrieve(url_vert, 'heroImage/vertImage/%d.jpg' %i['id'])

	urllib.urlretrieve(url_sb, 'heroImage/sbImage/%d.png' %i['id'])


	urllib.urlretrieve(url_lg, 'heroImage/lgImage/%d.png' %i['id'])
	if i['id'] == 2:
		break;

print 'allDone'

