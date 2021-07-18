package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
//        @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티(getter, setter)으로 입력해준다.
//        그리고 model 에 자동으로 객체를 넣어준다.

//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);

        itemRepository.save(item);
//      model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
//        @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티(getter, setter)으로 입력해준다.
//        그리고 model 에 자동으로 객체를 넣어준다. (default 클래스 이름을 앞글자만 소문자로 바꿔서 ex - Item > item)
        itemRepository.save(item);
        return "basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV4(Item item) {
//        @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티(getter, setter)으로 입력해준다.
//        그리고 model 에 자동으로 객체를 넣어준다. (default 클래스 이름을 앞글자만 소문자로 바꿔서 ex - Item > item)
//        @ModelAttribute 생략가능
        itemRepository.save(item);
        return "basic/item"; // 새로고침시 다시 post 요청이 오는 문제가 있음
    }

    //    @PostMapping("/add")
    public String addItemV5(Item item) {
//        @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티(getter, setter)으로 입력해준다.
//        그리고 model 에 자동으로 객체를 넣어준다. (default 클래스 이름을 앞글자만 소문자로 바꿔서 ex - Item > item)
//        @ModelAttribute 생략가능
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId(); // PRG(Post/Redirect/GET)
        // 지금은 url 인코딩이 안되는 문제가 있음
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
//        @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티(getter, setter)으로 입력해준다.
//        그리고 model 에 자동으로 객체를 넣어준다. (default 클래스 이름을 앞글자만 소문자로 바꿔서 ex - Item > item)
//        @ModelAttribute 생략가능
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}"; // PRG(Post/Redirect/GET) 
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}

