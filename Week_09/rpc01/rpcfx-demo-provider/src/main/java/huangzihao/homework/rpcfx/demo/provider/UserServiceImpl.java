package huangzihao.homework.rpcfx.demo.provider;

import huangzihao.homework.rpcfx.demo.api.User;
import huangzihao.homework.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(int id) {
        return new User(id, "KK " + System.currentTimeMillis());
    }
}
