import { useRef } from "react";

const handleInputChange = (formData, key) => {
  return (value) => {
    formData.current = {
      ...formData.current,
      [key]: value,
    };
  };
};

function useFormData(initialData = {}) {
  const formData = useRef(initialData);
  const getFormData = () => formData;
  return {
    getFormData,
    handleInputChange,
  };
}

export default useFormData;
