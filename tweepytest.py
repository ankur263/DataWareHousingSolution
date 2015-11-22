import time
import tweepy

consumer_key = 'bwKLCBo7eAeVa7RLrokJsW6sG'
consumer_secret = 'obxE6F6ZRaXCczJLSzdThISlV3VrRaeaWxQMPejkadfvndgquy'
access_token = '137553558-JDDDVNNXm6MgfUVfPh5W273azu4jc6d7R2WJBOAa'
access_token_secret = 'suRTonFPhaQomKy5hclprSnxSBTA36kEpPuEFRNm5Hhx6'
 
# OAuth process, using the keys and tokens
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)

user = api.get_user('vinayak205')
for friend in user.friends():
    print friend.screen_name
    time.sleep(60)
# ids = []
# for page in tweepy.Cursor(api.followers_ids, screen_name="ankur263").pages():
#     ids.extend(page)
#     #time.sleep(60)

# users = api.lookup_users(user_ids=ids)
# for u in users:
#     print u.screen_name
# for id in ids:
# 	print id