package sorting;
//Selection Sort (sorts from least to greatest)
private static void selectionSort(int[] numbers) {
  int length = numbers.length;
  for (int i = 0; i < length - 1; i++) {
      int min = numbers[i];
      int indexOfMin = i;
      for (int j = i + 1; j < length; j++) {
          if (numbers[j] < min) {
              min = numbers[j];
              indexOfMin = j;
          }
      }
      swap(numbers, i, indexOfMin);
  }
}

private static void swap(int[] numbers, int a, int b) {
  int temp = numbers[a];
  numbers[a] = numbers[b];
  numbers[b] = temp;
}
//insertion sort
private static void insertionSort(int[] inputArray) {
  for (int i = 1; i < inputArray.length; i++) {
      int currentValue = inputArray[i];
      int j = i - 1;
      while (j >= 0 && inputArray[j] > currentValue) {
          inputArray[j + 1] = inputArray[j];
          j--;
      }
      inputArray[j + 1] = currentValue;
  }
}
//bubble sort
boolean swappedSomething = true;

while (swappedSomething) {
  swappedSomething = false;
  for (int i = 0; i < numbers.length - 1; i++) {
      if (numbers[i] > numbers[i + 1]) {
          swappedSomething = true;
          int temp = numbers[i];
          numbers[i] = numbers[i + 1];
          numbers[i + 1] = temp;
      }
  }
}
//quickSort
private static void quicksort(int[] array, int lowIndex, int highIndex) {
  if (lowIndex >= highIndex) {
      return;
  }

  int pivot = array[highIndex];
  int leftPointer = lowIndex;
  int rightPointer = highIndex;

  while (leftPointer < rightPointer) {
      while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
          leftPointer++;
      }

      while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
          rightPointer--;
      }

      swap(array, leftPointer, rightPointer);
  }

  swap(array, leftPointer, highIndex);

  quicksort(array, lowIndex, leftPointer - 1);
  quicksort(array, leftPointer + 1, highIndex);
}

private static void swap(int[] array, int index1, int index2) {
  int temp = array[index1];
  array[index1] = array[index2];
  array[index2] = temp;
}
//Merge Sort
private static void mergeSort(int[] array) {

  int length = array.length;
  if (length <= 1) return;

  int middle = length / 2;

  int[] leftArray = new int[middle];
  int[] rightArray = new int[length - middle];

  for (int i = 0; i < length; i++) {
      if (i < middle) {
          leftArray[i] = array[i];
      } else {
          rightArray[i - middle] = array[i];
      }
  }

  mergeSort(leftArray);
  mergeSort(rightArray);
  merge(leftArray, rightArray, array);
}

private static void merge(int[] leftArray, int[] rightArray, int[] array) {
  int leftSize = leftArray.length;
  int rightSize = rightArray.length;
  int i = 0, l = 0, r = 0; // indices

  // merge the left and right arrays into the original array
  while (l < leftSize && r < rightSize) {
      if (leftArray[l] < rightArray[r]) {
          array[i] = leftArray[l];
          i++;
          l++;
      } else {
          array[i] = rightArray[r];
          i++;
          r++;
      }
  }

  // copy remaining elements of leftArray, if any
  while (l < leftSize) {
      array[i] = leftArray[l];
      i++;
      l++;
  }

  // copy remaining elements of rightArray, if any
  while (r < rightSize) {
      array[i] = rightArray[r];
      i++;
      r++;
  }
}
//Heap Sort
a  public static void heapSort(int[] arr) {
      int n = arr.length;

      // Build max heap
      for (int i = n / 2 - 1; i >= 0; i--) {
          heapify(arr, n, i);
      }

      // Extract elements from heap one by one
      for (int i = n - 1; i > 0; i--) {
          // Move current root to end
          int temp = arr[0];
          arr[0] = arr[i];
          arr[i] = temp;

          // call max heapify on the reduced heap
          heapify(arr, i, 0);
      }
  }

  public static void heapify(int[] arr, int n, int i) {
      int largest = i; // Initialize largest as root
      int left = 2 * i + 1; // Left child
      int right = 2 * i + 2; // Right child

      // If left child is larger than root
      if (left < n && arr[left] > arr[largest]) {
          largest = left;
      }

      // If right child is larger than largest so far
      if (right < n && arr[right] > arr[largest]) {
          largest = right;
      }

      // If largest is not root
      if (largest != i) {
          int temp = arr[i];
          arr[i] = arr[largest];
          arr[largest] = temp;

          // Recursively heapify the affected sub-tree
          heapify(arr, n, largest);
      }
  }
//Radix Sort
import java.util.Arrays;

public class RadixSort {

  public static void radixSort(int[] arr) {
      if (arr == null || arr.length == 0) {
          return;
      }

      int max = Arrays.stream(arr).max().getAsInt();
      for (int exp = 1; max / exp > 0; exp *= 10) {
          countSort(arr, exp);
      }
  }

  private static void countSort(int[] arr, int exp) {
      int n = arr.length;
      int[] output = new int[n];
      int[] count = new int[10];

      Arrays.fill(count, 0);

      for (int value : arr) {
          count[(value / exp) % 10]++;
      }

      for (int i = 1; i < 10; i++) {
          count[i] += count[i - 1];
      }

      for (int i = n - 1; i >= 0; i--) {
          output[count[(arr[i] / exp) % 10] - 1] = arr[i];
          count[(arr[i] / exp) % 10]--;
      }

      System.arraycopy(output, 0, arr, 0, n);
  }

  public static void main(String[] args) {
      int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
      radixSort(arr);
      System.out.println("Sorted array: " + Arrays.toString(arr));
  }
}
//Counting sort
import java.util.Arrays;

public class CountingSort {

  public static void countingSort(int[] arr) {
      int max = Arrays.stream(arr).max().getAsInt();
      int min = Arrays.stream(arr).min().getAsInt();
      int range = max - min + 1;
      int[] count = new int[range];
      int[] output = new int[arr.length];

      for (int value : arr) {
          count[value - min]++;
      }

      for (int i = 1; i < range; i++) {
          count[i] += count[i - 1];
      }

      for (int i = arr.length - 1; i >= 0; i--) {
          output[count[arr[i] - min] - 1] = arr[i];
          count[arr[i] - min]--;
      }

      System.arraycopy(output, 0, arr, 0, arr.length);
  }

  public static void main(String[] args) {
      int[] arr = {4, 2, 2, 8, 3, 3, 1};
      countingSort(arr);
      System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}

