# Automatic test oracle generation for SE/CT

## Task 1

No, examples below.

Also, you can run them [here](src/test/java/org/example/TaskOneExamples.java).

#### 1. Arrays

 ```java
int[]a=new int[]{1,2};
int[]b=new int[]{1,2,3};
System.out.println(a.equals(b)); // false
```

Instead of `equals` we should use `Arrays::equals` and `Arrays::deepEquals` for multidimensional
arrays.

#### 2. Floating-point numbers

```java
Double a=0.3;
Double b=0.2+0.1;
System.out.println(a.equals(b)); // false
```

Technically, it's correct behaviour. However, in most cases we don't want exact compassing.

#### 3. `equals` works as identity

## Task 2

Arrays and floating-point numbers just have to be compared with specific methods. So I won't make
remarks about them further.

### 0. Things before comparing

1. Check identity. If it's true, no need to do other work.
2. Check `equals` is reflexive: if `!a.equals(a)` or `!b.equals(b)` then it's a bug in program,
   which may affected comparing objects
3. Check if `equals` is symmetric: if `a.equals(b) != b.equals(a)` then same as in 2.

### 1. Using `equals` if objects are instances of standard libraries classes

No need to do complex work to check two `String` or `Integer` objects.

I will call such classes and their instances 'trusted'. Other are 'non-trusted'.

#### Pros:

- Don't repeat work already made by other smart people.

#### Cons:

- Require additional checking for classes, calling `equals` on possibly non-trusted objects.
  On example of `List<Number>`:
  For runtime it's just a `List<Object>`. To ensure that `equals` won't call `equals` from
  non-trusted
  classes, we have to check every element of list.
- Using reflection such operation could be done for all classes: get all accessible objects and
  check them.
    - But this method may cause false negatives: object may contain `Object` fields, but don't use
      them in `equals`
    - There can occur links cycle. Have to handle it.
    - So accurate check may be done only in source code.
- Alternative approach is to write such check for every problem class we want. One for `Collection`,
  one for `Map`, etc.

### 2. Compare fields using reflection

If some fields can't be compared by other methods, then use this method.

#### Pros:

- No false positives. If all fields are equal, then objects are equal
- Works for all objects
    - No problems with generics.

#### Cons:

- May not satisfy `equals` contract. Two 'equal' objects may have different hashCode. For example:
  if `hashCode` is from `java.lang.Object`
- Works clearly only if objects are exactly same class (can't compare `ArrayList` and `LinkedList`)
- Have to handle links cycles.
- May occurs false negatives. Example:

 ```java
var list100=new ArrayList<Integer>(100); // capacity 100

var list5=new ArrayList<Integer>(5); // capacity 5
```

### 3. Using `hashCode` and `toString`

#### Pros:

- No false negatives: if hash-codes are different, but objects are 'equal', then it's a bug
  in `hashCode` and violation of its contract.

#### Cons:

- False positives: two different objects can have the same hash code.\
  In that case `toString` may help: if results of `toString` are different then objects are
  different.
- For arrays should be used `Arrays::hashCode` and `Arrays::deepHashCode` respectively

## Task 3.

### Similarity definition

My definition of 'similarity': `BinaryTree` `a` have similar contents with `BinaryTree` `b` iff

for all int x:

1. `a.contains(x) == b.contains(x)`
2. `a' = a.remove(x)`, `b' = b.remove(x)` \
   `a'` have similar contents with `b'`

Thus leads to necessary and sufficient condition:  

forall int x: `a.count(x) == b.count(x)`

### Code

BinaryTree [abstract class](src/main/java/org/example/BinaryTree.java). `contentsSimilar` is here.

BinaryTree [realization](src/main/java/org/example/BinaryTreeImpl.java). Used for testing.

[Tests](src/test/java/org/example/BinaryTreeTest.java)
