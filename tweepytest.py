import time
import tweepy

consumer_key = //put your key here
consumer_secret = //
access_token = //
access_token_secret = //
 
# OAuth process, using the keys and tokens
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)

user = api.get_user('friend_twitter_handle')
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
