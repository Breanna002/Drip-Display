# Drip-Display
Original App Design Project - README Template
===

# Drip Display

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Fashion blogging app created for users to post, buy, and sell different fashion/designer pieces at their own leisure as well as connecting with other users within the app creating groups and having conversations about fashion ideas, upcoming relaeases, etc.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Fashion/social networking 
- **Mobile:** this app will be primarily developed for mobile use. Functionality will be limited to mobile devices.
- **Story:**  Users will be able to post pictures of their outfits, shoes, or own personal brands. They will also be able to add tags over pieces of clothing or shoes, therefore, when clicking on the items it will show any information provided by the user such as: Brand, Name of shoe or clothing item, link to purchase, etc. Users will also be able to follow each other & create joinable or invitational groups based upon similar interests such as: streetwear, formal wear, aesthetic, sneakers, dress shoes, etc.
- **Market:** any individual could use this app, but to keep it a safe environment there will be an age restriction.
- **Habit:** this app could be used as often as the user wants depending on how much they shop and are browsing for new fashion items. 
- **Scope:** First we would start with the different clothing categories based on taste in clothing and/or brands, then perhaps this could filter or narrow down the items the user is in search of, but also giving the users a chance to broaden their usage with all of the items and options given. Large potential use with ASOS, Fashion Nova, and other fashion/ clothing applications.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] User can create an account
- [x] User can login
Here's a walkthrough of implemented user stories:

<img src= 'https://github.com/Breanna002/Drip-Display/blob/main/Seminar1stGif.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

- [x] User can logout
- [x] User can post fashion items
<img src= 'https://github.com/Breanna002/Drip-Display/blob/main/Seminar2ndGif.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
- [x] User can tag items in photos
<img src = 'https://github.com/Breanna002/Drip-Display/blob/main/Seminar3rdGif.gif' title= 'Video Walkthrough' width= '' alt= 'Video Walkthroug/>
- [] User can follow other users 
- [] User can access fashion item website
- [] User can comment on other users post
- [] User can like other users photos 

**Optional Nice-to-have Stories**

### 2. Screen Archetypes

Login Screen
* User can login
* User can click create account
Create Account Screen
* User can create a new account
* User can be lead to stream after creating the account
Stream
* User can view users photos on their stream
* User can click the link under the user post to be lead to their users website. 
* User can double tap a photo to like
Creation
* User can post a new photo of their fashion items to their feed
* User can tag other users with their photo. 
Search
* User can search for other users fashion items 
* User can follow/unfollow another user

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home Button
* Search clothing Item
* Post merchandise 
* Profile

**Flow Navigation** (Screen to Screen)

Login Screen
* Home
Registration Screen
* Home
Home Screen 
* Profile
Profile 
* Creation
Creation Screen
* Feed
Feed Screen 
* Home
Search Screen
* None
## Wireframes
[Add picture of your hand sketched wireframes in this section]

![](https://i.imgur.com/BRr680o.jpg)



### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models
User:

| Property        | Type                   | Description                 |
| --------------- | ---------------------- | --------------------------- |
| UserID          | String                 | Unique ID for specific user |
| Username  | String | User created user name                            |
| Password        | String                 | User created password       |
| Email           | String                 | User given email address    |
| FirstName       | String                 | User given first name       |
| LastName        | String                 | User given lst name         |


Post:

| Property      | Type    | Description                          |
| ------------- | ------- | ------------------------------------ |
| PostId | String |ID of a specific post   |
| Author |Pointer to User | Image author |
| Date   |DateTime | Date & Time when post was created           |
| Image  | File    | Image that user posts                |
| Caption| String  | User caption for post                |
| LikesCount    | Number  | Number of likes on a post            |
| CommentsCount | Number  | Number of comments on a post         |
| Tags | Pointer | The tags that users insert for sales |

Tags:


|Property    | Type        | Description                                             |
| ----------- | --------------- | ---------------------------------------------------- |
| TagId       | String          | ID of specific tag added to post                     |
| Parent      | Pointer to Post | Post that tag is connected to                        |
| Image       | File            | User uploaded image of specific item being displayed |
| Description | String          | User description of tagged item                      |
| Link        | String          | Link to where tagged item can be purchased           |


### Networking
Home Feed Screen
* (Read/GET) Query all posts where user is author

let query = PFQuery(className:"Post")
query.whereKey("author", equalTo: currentUser)
query.order(byDescending: "createdAt")
query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
   if let error = error { 
      print(error.localizedDescription)
   } else if let posts = posts {
      print("Successfully retrieved \(posts.count) posts.")
  // TODO: Do something with posts...
   }
}

* (Create/POST) Create a new like on a post
* (Delete) Delete existing like
* (Create/POST) Create a new comment on a post
* (Delete) Delete existing comment

Create Post Screen
* (Create/POST) Create a new post object
* (Create/POST) Create a new Caption
* (Create/POST) Create a new tag for item
* (Delete) Delete existing tag 
* (Search/Get) Search item/ people

Profile Screen
* (Read/GET) Query logged in user object
* (Update/PUT) Update user profile image
* (Update/PUT) update username
* (Create/PUT) Create password
- [OPTIONAL: List endpoints if using existing API such as Yelp]
