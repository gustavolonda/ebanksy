import React from "react";
import { render, waitForElement } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import AccountRead , {urlGet,fetchAccounts} from '../account/read/read';
import axios from "axios";


jest.mock("axios");

describe("GetAccount", () => {
  describe("when API call is successful", () => {
    it("should return account list", async () => {
      // given
      const accountList = [
        {
            id: "3766a167-7644-48c5-8b56-916df169925d",
            accountNum: "91178410051695462181",
            accountType: "S",
            initialBalance: 1566.0,
            status: false,
            customerName: "Luis Dominguez",
            customerId: "f30b5ba9-856e-41ee-ad73-956fc37de08a",
            availableBalance: 1542.0
        },
        {
            id: "b4d3db1c-2d7d-4a35-aa9a-e0568bb6953a",
            accountNum: "35688386311620908391",
            accountType: "S",
            initialBalance: 124.0,
            status: false,
            customerName: "Juan Pico",
            customerId: "3c068b0a-d212-456a-9faa-13b2c7614e77",
            availableBalance: 65.0
        }
        
    ];
      axios.get.mockResolvedValueOnce(accountList);

      // when
      const result = await fetchAccounts();

      // then
      expect(axios.get).toHaveBeenCalledWith(`${urlGet}`);
      expect(result).toEqual(accountList);
    });
  });
});