// 声明这是一个控制器类
@Controller
// 映射URL到该控制器类
@RequestMapping("/example")
public class ExampleController {

    // 自动装配Bean
    @Autowired
    private ExampleService exampleService;

    // 手动装配Bean
    @Resource(name = "exampleService")
    private ExampleService exampleService2;


    // 映射URL到该方法
    @RequestMapping(method = RequestMethod.GET)
    public String examplePage(Model model) {
        List<Example> examples = exampleService.getAllExamples();
        // 添加数据到Model中
        model.addAttribute("examples", examples);
        // 返回视图名称
        return "examplePage";
    }


    // 映射URL到该方法
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String exampleDetails(@PathVariable("id") int id, Model model) {
        // 使用 @PathVariable 注解将 URL 路径中的变量绑定到方法参数
        Example example = exampleService.getExampleById(id);
        // 添加数据到Model中
        model.addAttribute("example", example);
        // 返回视图名称
        return "exampleDetails";
    }


    // 映射URL到该方法
    @RequestMapping(method = RequestMethod.POST)
    public String addExample(@ModelAttribute("example") Example example, BindingResult result) {
        // 绑定表单数据到模型对象
        if (result.hasErrors()) {
            return "addExample";
        }
        exampleService.addExample(example);
        // 重定向到指定URL
        return "redirect:/example";
    }


    // 映射URL到该方法
    @RequestMapping(value = "/exampleJson", method = RequestMethod.GET)
    // 将方法返回值作为响应体发送给客户端
    @ResponseBody
    public String exampleJson() {
        // 返回JSON格式的字符串
        return "{\"message\": \"Hello, World!\"}";
    }


    // 使用 @RequestMapping 注解映射 POST 请求到这个方法
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    // 使用 @ResponseBody 注解将返回值转换为响应正文    
    public @ResponseBody String postExample(@RequestBody String body) {
        // 使用 @RequestBody 注解将请求正文转换为方法参数
        return "Received body: " + body;
    }


    // 注册自定义编辑器
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}




//  @RestController 注解是一个组合注解，它相当于同时使用了 @Controller 和 @ResponseBody 两个注解。
//  当你在一个控制器类上使用 @RestController 注解时，
//  这个控制器类中的所有方法都会自动使用 @ResponseBody 注解，将返回值转换为响应正文。
@RestController
@RequestMapping("/example")
public class ExampleRestController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getExample() {
        return "This is an example";
    }
}

