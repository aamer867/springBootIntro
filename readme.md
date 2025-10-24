# 🧩 Spring MVC Annotations (from "@RestController in a Nutshell")

| Annotation | Based On / Combines | Purpose | Usage in Context | Default Behavior |
|-------------|---------------------|----------|------------------|------------------|
| `@Component` | — | Marks a class as a Spring-managed Bean. Enables Spring to detect and instantiate it during startup. | Used for any general-purpose class managed by the Spring IoC container. | Becomes a managed Spring Bean. |
| `@Controller` | Alias for `@Component` | Defines a class as a **Spring MVC controller** that handles web requests and returns **views** (HTML). | Used when building traditional MVC apps with view templates. Works with a `Model` and `ViewResolver`. | Returns rendered **HTML pages**. |
| `@ResponseBody` | — | Instructs Spring to send the **method’s return value directly in the HTTP response body**, usually as JSON or XML. | Used inside `@Controller` classes when returning **data** instead of HTML. | Returns **JSON** by default. |
| `@RestController` | Combines `@Controller` + `@ResponseBody` | Simplifies REST API creation by making all controller methods return **data (JSON/XML)** automatically. | Used for **RESTful web services** that expose data endpoints. | Returns **JSON** responses by default. |
| `@RequestMapping` | — | Maps a method or class to a specific URL path and HTTP method(s). Can handle GET, POST, PUT, DELETE, etc. | Example: `@RequestMapping(path="/coffees", method=RequestMethod.GET)` → Responds to `/coffees` with GET only. Spring automatically converts Java objects to JSON (marshalling) and parses JSON into objects (unmarshalling). | Depends on method and path; can handle multiple HTTP methods. |
| `@GetMapping` | Shortcut for `@RequestMapping(method = RequestMethod.GET)` | Handles HTTP GET requests to the specified path. Simplifies `@RequestMapping` by removing the need to specify the method. | Example: `@GetMapping("/coffees")` → Responds to GET requests at `/coffees`. Reduces boilerplate and improves readability. | GET request mapping by default. |

---

## 🧠 Summary

- `@Controller` → For MVC (web pages)  
- `@ResponseBody` → For JSON/XML data inside a controller  
- `@RestController` → Shortcut for both, used for REST APIs  
- `@Component` → Base stereotype that makes Spring manage the class  
- `@RequestMapping` → Maps URL paths + HTTP methods (more flexible)  
- `@GetMapping` → Simplified GET request mapping for REST endpoinds

