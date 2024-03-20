<h1 align="center">Record Job</h1>

<br>

## ❯ Getting Started
#### Getting project:
```bash
git clone https://...
```
#### Configuring job time for run
```bash
folder: resources/jobs.properties
```
#### The project is ready to run



## ❯ Services
#### List of available services

<details>
 <summary><code>GET</code> <code>job/getStatus</code> - Get status job </summary>

##### Params
> |    Name    | mandatory | data type | description |
> |------------|-----------|-----------|-------------|
> | jobName | not required | String    | Job Name  |

##### Example cURL
> ```javascript
> curl --location 'localhost:8081/job/getStatus'
> ```
</details>

<details>
 <summary><code>GET</code> <code>job/startJob</code> - Start job </summary>

##### Params
> |    Name    | mandatory | data type | description |
> |------------|-----------|-----------|-------------|
> | jobName | not required | String    | Job Name  |

##### Example cURL
> ```javascript
> curl --location 'localhost:8081/job/startJob'
> ```
</details>

<details>
 <summary><code>GET</code> <code>job/stopJob</code> - Stop job </summary>

##### Params
> |    Name    | mandatory | data type | description |
> |------------|-----------|-----------|-------------|
> | jobName | not required | String    | Job Name  |

##### Example cURL
> ```javascript
> curl --location 'localhost:8081/job/stopJob'
> ```
</details>

<details>
 <summary><code>GET</code> <code>job/getJob</code> - Get available job list </summary>

##### Example cURL
> ```javascript
> curl --location 'localhost:8081/job/getJob'
> ```
</details>

<details>
 <summary><code>GET</code> <code>records</code> - Get record list </summary>

##### Params
> | Name | mandatory | data type | description |
> |------------|-----------|-----------|-------------|
> | page    | required | Number    | page number  |
> | limit   | required | Number    | limit of maximum data page size  |

##### Example cURL
> ```javascript
> curl --location 'localhost:8081/records?page=0&limit=50'
> ```
</details>

<details>
 <summary><code>POST</code> <code>records</code> - Create a new record </summary>

##### Body
> | name     | mandatory | data type | description    |
> |----------|-----------|----------------|--------------|
> | title    | required | String    | record title   |
> | content  | required | String    | record content |

##### Example cURL
> ```javascript
> curl --location 'localhost:8081/records' \
> --header 'Content-Type: application/json' \
> --data '{
> "title": "small test",
> "content": "this is small test"
> }'
> ```
</details>



## ❯ Features
- Request validation
- Logging request response with as Aspect
- Database H2
- DDD - Domain Driven Design pattern
- Handling exception
- Job with Quartz
- Ready for multiple languages
- JPA

In order to prioritize the completion of the project's main functionalities, I have deferred the implementation of certain production-ready level features that are environment-dependent.
- Authentication and Authorization
- Docker support
- Continuous integration
- API document

## ❯ Contributing
Contributions are more than welcome! Please contact address
[vutukien1995@gmail.com.vn](vutukien1995@gmail.com.vn)
