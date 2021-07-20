package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(itemValidator);
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Item 뒤에 BindingResult 가 있어야 됨. 순서 중요!

//        log.info("objectName={}", bindingResult.getObjectName());
//        log.info("target={}", bindingResult.getTarget());
//
//        // 검증 로직
//        if (!StringUtils.hasText(item.getItemName())) {
////            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
////            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
////            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
//            bindingResult.rejectValue("itemName", "required");
//        }
////        위의 if 문과 같은 코드
////        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");
//
//        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
////            bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 1,000,000 까지 입니다."));
////            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000 ~ 1,000,000 까지 입니다."));
////            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
//            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
//        }
//        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
////            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999개 까지입니다."));
////            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 최대 9,999개 까지입니다."));
////            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));
//            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
//        }
//
//        // 특정 필드가 아닌 복합 롤 검증
//        if (item.getPrice() != null && item.getQuantity() != null) {
//            int resultPrice = item.getPrice() * item.getQuantity();
//            if (resultPrice < 10000) {
////                bindingResult.addError(new ObjectError("item", "가격 X 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
////                bindingResult.addError(new ObjectError("item", null, null, "가격 X 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
////                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
//                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
//            }
//        }

        // 위의 주석을 아래의 코드로 변경
//        itemValidator.validate(item, bindingResult);
        // 아래 코드 추가로 위의 코드 주석 처리
//        @InitBinder
//        public void init(WebDataBinder dataBinder) {
//            dataBinder.addValidators(itemValidator);
//        }

        // 검증 실패 시
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

