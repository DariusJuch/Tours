export default function Button({ children, type = 'button', onClick, isFull = false, disabled = false }) {
  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      className={`px-4 py-2 rounded-lg font-medium transition duration-200 ${
        isFull ? 'w-full' : ''
      } ${disabled
        ? 'bg-gray-400 cursor-not-allowed text-white'
        : 'bg-blue-600 hover:bg-blue-700 text-white'
      }`}
    >
      {children}
    </button>
  );
}