package midtermProject.BankingSystem.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import midtermProject.BankingSystem.controller.dto.AccountBalanceDTO;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.CheckingAccount;
import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.repository.UsersRepository.AccountHoldersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@SpringBootTest
class AccountControllerTest {
    @Autowired
    AccountHoldersRepository accountHoldersRepository;

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.registerModule(new JavaTimeModule());


    }


    @Test
    void getAllAccounts_validRequest_allAccounts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //System.out.println(mvcResult.getResponse().getContentAsString());
        String responseContent = mvcResult.getResponse().getContentAsString();

        assertTrue(responseContent.contains("Jarko"));
        assertTrue(responseContent.contains("David"));
        assertTrue(responseContent.contains("Adrian"));
    }

    @Test
    void getAccountByNumber_validNumber_correctAccount() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts/4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

        assertTrue(responseContent.contains("DavidTA"));
        assertTrue(responseContent.contains("2001-10-10"));
        assertTrue(responseContent.contains("1000.00"));
        assertFalse(responseContent.contains("ABCD"));
    }
    @Test
    void getAccountByNumber_invalidNumber_BadRequest() throws Exception {

        mockMvc.perform(get("/accounts/40").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void getAccountBalance_validNumber_balance() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/balance/3"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertFalse(responseContent.contains("EUR"));
        assertFalse(responseContent.contains("1000.00"));

        assertTrue(responseContent.contains("USD"));
        assertTrue(responseContent.contains("0.00"));
    }

    @Test
    void saveCheckingAccount_validRequest_successful() throws Exception {
        Optional <AccountHolders> optionalAccountHolders = accountHoldersRepository.findById(7);
        CheckingAccount checkingAccount = new CheckingAccount(new Money(new BigDecimal(400.00)), "key1234", optionalAccountHolders.get());

        String body = objectMapper.writeValueAsString(checkingAccount);

        mockMvc.perform(post("/accounts/checking-accounts").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateAccountBalance_newBalance_balanceUpdated() throws Exception {
        Money mount = new Money(new BigDecimal(8880.0));
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO(mount.getAmount(), mount.getCurrency());
        String body = objectMapper.writeValueAsString(accountBalanceDTO);

        mockMvc.perform(patch("/accounts/balance/1").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        String responseContent = mvcResult.getResponse().getContentAsString();

        assertTrue(responseContent.contains("8880.0"));

    }

    @Test
    void deleteAccount_validNumber_deleteAccount() throws Exception{
        mockMvc.perform(delete("/accounts/delete/2"))
                .andExpect(status().isNoContent())
                .andReturn();
        mockMvc.perform(get("/accounts/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deleteAccount_invalidNumber_notFound() throws Exception{
        mockMvc.perform(delete("/accounts/delete/50"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}