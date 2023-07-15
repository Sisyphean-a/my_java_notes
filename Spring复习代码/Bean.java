// 常见的Bean注解
@Component // 通用注解
@Repository // 用于将DAO层标记为Bean，用于数据交互
@Service	// 用于将业务逻辑层标记为Bean，通常用于封装业务逻辑
@Controller // 用于将表示层标记为Bean，用于处理Http请求和响应
@RestController  // 这个注解是@Controller和@ResponseBody的组合，
// 用于将表示层类标记为Bean。它通常用于构建RESTful Web服务。


// 简单的JDBC操作
// JDBCTemplate
// query  queryForObject queryForList update execute

@Repository
public class MyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM mytable");
    }

    public int update(String value, int id) {
        return jdbcTemplate.update("UPDATE mytable SET mycolumn = ? WHERE id = ?", value, id);
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM mytable WHERE id = ?", id);
    }
}




@Service
public class MyService {
    @Autowired
    private MyDao myDao;

    public void myMethod() {
        // 查询
        List<Map<String, Object>> result = myDao.findAll();

        // 更新
        int rowsAffected = myDao.update("myvalue", 1);

        // 删除
        int rowsDeleted = myDao.delete(1);
    }
}
