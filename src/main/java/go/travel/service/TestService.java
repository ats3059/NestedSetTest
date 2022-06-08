package go.travel.service;

import go.travel.dto.ShopDto;
import go.travel.repository.MemberRepository;
import go.travel.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestService {


    private final TestRepository testRepository;

    public ShopDto testShop() {
        ShopDto shopDto = new ShopDto(testRepository.shopTest());
        return shopDto;
    }

}
