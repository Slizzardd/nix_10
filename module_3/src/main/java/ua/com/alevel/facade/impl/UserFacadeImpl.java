package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto req) {
        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setAge(req.getAge());
        user.setEmail(req.getEmail());
        user.setPassportDetails(req.getPassport_INN());
        user.setPhoneNumber(req.getPhoneNumber());
        userService.create(user);
    }

    @Override
    public void update(UserRequestDto req, Long id) {
        User user = userService.findById(id);
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setAge(req.getAge());
        user.setEmail(req.getEmail());
        user.setPassportDetails(req.getPassport_INN());
        user.setPhoneNumber(req.getPhoneNumber());
        userService.update(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public Map<Long, String> findAccountsByUserId(Long id) {
        return userService.findAccountsByUserId(id);
    }

    @Override
    public List<Account> findAllAccountsByUserId(Long userId) {
        return userService.findAllAccountsByUserId(userId);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(userService.findById(id));
    }

    @Override
    public PageData<UserResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<User> tableResponse = userService.findAll(dataTableRequest);

        List<UserResponseDto> drivers = tableResponse.getItems().stream().
                map(UserResponseDto::new).
                peek(driverResponseDto -> driverResponseDto.setAccountCount((Integer) tableResponse.
                        getOtherParamMap().get(driverResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<UserResponseDto> pageData = (PageData<UserResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(drivers);

        return pageData;
    }
}
