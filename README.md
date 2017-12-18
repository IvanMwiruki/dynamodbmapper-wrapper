# dynamodbmapper-wrapper 
A simple wrapper around [DynamoDBMapper][1] that adds some enhancements.

## Enhancements
- Narrowed api: infrequently used operations (such as batchSave, batchDelete, count, etc.) left out

- `save(...)` now returns the object that was saved instead of void. Useful for working with the object after DynamoDB has initialized auto generated keys, dates, version number, etc.

- `load(...)` now returns an Optional instead of an object or null. More clearly states that the operation may return nothing. 

- Improved documentation, including: javadocs now state the actual low level operations being performed
    - Warning: as always, implementation details are subject to change. Though they're unlikely to in this case.
    
- Direct access to `updateItem(...)` from the [low level interface][2]

[1]: http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/dynamodbv2/datamodeling/DynamoDBMapper.html
[2]: http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Programming.SDKs.Interfaces.LowLevel.html