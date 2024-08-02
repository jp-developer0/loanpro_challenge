import React, { useState } from "react";
import Select from "react-select";

const options = [
  { value: 1, label: "addition" },
  { value: 1, label: "subtraction" },
  { value: 1, label: "multiplication" },
  { value: 1, label: "division" },
  { value: 1, label: "square_root" },
  { value: 1, label: "random_string" }
];

const changeing = [
  { value: 80, label: "Indian Rupee" },
  { value: 50, label: "Nepal" },
];

const CurrencyConverter = () => {
  const [selectedOption, setSelectedOption] = useState({
    fromCurrency: null,
    toCurrency: null,
    amount: "",
  });
  const [convertedAmount, setConvertedAmount] = useState("");

  const handleChnage = (name, value) => {
    setSelectedOption({ ...selectedOption, [name]: value });
  };

  const handleAmountChange = (e) => {
    const amount = e.target.value;
    setSelectedOption({ ...selectedOption, amount });
    
  };

  const handleCurrencyChange = (name, selectedOption) => {
    handleChnage(name, selectedOption);
  };

  const handleCalculate = () => {
    const { fromCurrency, toCurrency, amount } = selectedOption;

    if (fromCurrency && toCurrency && amount) {
      const conversionRate = toCurrency.value / fromCurrency.value;
      const convertedAmount = amount * conversionRate;
      setConvertedAmount(convertedAmount.toFixed(2));
    } else {
      setConvertedAmount("");
    }
  };

  return (
    <>
      <div className="bg-[#189968] flex m-12 flex-col justify-center items-center p-4 gap-4 font-calibri rounded-xl">
        <h1 className="text-3xl">Operation</h1>
        <div className="flex gap-5 justify-center items-center">
        <label>Enter First Number</label>
          <input
            type="text"
            className="rounded-lg h-10"
            value={selectedOption.amount}
            onChange={handleAmountChange}
          />
          
        </div>
        <div className="flex gap-5 justify-center items-center">
        <label>Enter Second Number</label>
          <input
            type="text"
            className="rounded-lg h-10"
            value={selectedOption.amount}
            onChange={handleAmountChange}
          />
  
        </div>
        
        <div className="flex gap-5 justify-center items-center">
          <label>Select Operation</label>
          <Select
            className="rounded-2xl w-56"
            value={selectedOption.fromCurrency}
            onChange={(selectedOption) =>
              handleCurrencyChange("fromCurrency", selectedOption)
            }
            options={options}
            isClearable
          />
        </div>

        <button
          className="bg-white p-3 rounded-lg font-calibri font-bold"
          onClick={handleCalculate}
        >
          Calculate
        </button>

        {convertedAmount && (
          <p className="text-xl mt-4">
            Converted Amount: {convertedAmount}
          </p>
        )}
      </div>
    </>
  );
};

export default CurrencyConverter;
