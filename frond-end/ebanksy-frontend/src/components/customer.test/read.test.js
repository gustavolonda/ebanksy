import React from "react";
import { render, waitForElement } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import  CustomerRead, {urlGet,fetchCustomers} from '../customer/read/read';
import axios from "axios";


jest.mock("axios");

describe("GetAccount", () => {
  describe("when API call is successful", () => {
    it("should return account list", async () => {
      // given
      const customerList = [
        {
            id: "c5ef5ceb-39ec-486c-97a6-ec17fddb6b9d",
            name: "Julio Romero",
            address: "hgfhghfh",
            password: "dsfdsfdsf",
            gender: "",
            age: 42,
            idCard: "456546546",
            phone: "09765544",
            status: true
        },
        {
            id: "132be291-6631-4da4-961b-8affaf9937ac",
            name: "Balero Estacio",
            address: "23",
            password: "fdgdfgrtgt",
            gender: "",
            age: 78,
            idCard: "09856454",
            phone: "09875665523",
            status: true
        }
        
    ];
      axios.get.mockResolvedValueOnce(customerList);

      // when
      const result = await fetchCustomers();

      // then
      expect(axios.get).toHaveBeenCalledWith(`${urlGet}`);
      expect(result).toEqual(customerList);
    });
  });
});