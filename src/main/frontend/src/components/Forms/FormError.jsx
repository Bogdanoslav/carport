function FormError({ validation }) {
  const error = validation.isInvalid ? (
    <div className="alert alert-danger">{validation.message}</div>
  ) : (
    ""
  );
  return error;
}

export default FormError;
