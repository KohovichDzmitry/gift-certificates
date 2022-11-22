## Gift Certificate

### Work with gift certificates
 > GiftCertificate:
     id,
     name,
     description,
     price,
     duration,
     createDate,
     lastUpdateDate,
     tags

***GET***

* ***find all gift certificates***
```sh
GET/api/v1/gift-certificates
```
* ***find gift certificate by id***
```sh
GET/api/v1/gift-certificates/{id}
```
* ***find gift certificate by part of name or description***
```sh
GET/api/v1/gift-certificates/part
```
* ***find gift certificate by tag name***
```sh
GET/api/v1/gift-certificates/tag/{tagName}
```
* ***find gift certificate by several tag names***
```sh
GET/api/v1/gift-certificates/tags
```
***POST***

* ***add gift certificate***
```sh
POST/api/v1/gift-certificates
```
***UPDATE***

* ***update gift certificate***
```sh
PATCH/api/v1/gift-certificates/{id}
```
***DELETE***

* ***delete gift certificate***
```sh
DELETE/api/v1/gift-certificates/{id}
```
### Work with tags
  >Tag:
      id,
      name

***GET***

* ***find all tags***
```sh
GET/api/v1/tags
```
* ***find tag by id***
```sh
GET/api/v1/tags/{id}
```
* ***find most widely used tag***
```sh
GET/api/v1/mostWidelyUsedTag
```
***POST***

* ***add tag***
```sh
POST/api/v1/tags
```
***UPDATE***

* ***update tag***
```sh
PATCH/api/v1/tags/{id}
```
***DELETE***

* ***delete tag***
```sh
DELETE/api/v1/tags/{id}
```
### Work with orders
  >Order:
      id,
      cost,
      purchaseTimestamp,
      user,
      giftCertificate

***GET***

* ***find all orders***
```sh
GET/api/v1/orders
```
* ***find order by id***
```sh
GET/api/v1/orders/{id}
```
* ***find all user orders***
```sh
GET/api/v1/orders/userId/{userId}
```
***POST***

* ***add order***
```sh
POST/api/v1/orders/
```
### Work with users
  >User:
      id,
      name,
      orders

***GET***

* ***find all users***
```sh
GET/api/v1/users
```
* ***find user by id***
```sh
GET/api/v1/users/{id}
```
* ***find user by name***
```sh
GET/api/v1/users/name/{userName}
```