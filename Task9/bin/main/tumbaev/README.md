# Application for checking parenthesis, braces and brackets sequences in a text

This is a work for [Sber code beauty contest](https://beautifulcode.sber.ru/task/java).

## Project Functionality

The application checks whether the parentheses in the text are placed correctly or not.

* Supports `(`, `{` and `[` types.
* Empty brackets they are considered invalid.
* Maximum length of the text - **7000** symbols

### Examples

1. `Hello world` - valid
2. `(Hello) {world}` - valid
3. `({[Hello]})` - valid
4. `(Hello) {world{}}` - invalid
5. `(Hello) {world} []` - invalid
6. `(Hello` - invalid
7. `Hello)` - invalid

## API

### Check brackets

* `Content-type` header must contain `application/json`.
* `Accept` must either contain `application/json` or not be present.

```http request
POST http://localhost:8080/api/checkBrackets
Content-Type: application/json
```

```json
{
  //Must not be null, empty or contain only whitspace characters. Max length -  7000 symbols
  "text": "(Hello) {world}"
}
```

#### Responses

* **200:**
  `true` if provided sequence was correct, `false` otherwise

```json
{
  "isCorrect": true
}
```

* **Bad request (400):**
    * Invalid json in the body
    * Text is null, empty, or contains only whitespace characters
    * Text is too long

* **Not found (404):** Uri was not `/api/checkBrackets`

* **Method not allowed (405):** Provided http method is not POST

* **Unsupported media type exception (415):**
    * `Content-type` header didn't contain `application/json`
    * `Accept` header was present and didn't contain `application/json`

* **Internal server error (500):** Internal server error

### Error response

If response status is not 2xx, the body will contain the following object:

```json
{
  //or other code, depending on response status
  "code": "INTERNAL_SERVER_ERROR",
  "message": "general information about what went wrong",
  "details": "details about the error"
}
```
