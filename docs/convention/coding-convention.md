# コーディング規約

## 共通

- プログラミング言語に一般的なコーディング規約が存在するならばそれに従う

<br>

## 一般

- インデントはスペース 4 つ
- 処理効率よりもコードの読みやすさを優先する

<br>

## 制御構文

- if や while の後ろにはスペースを 1 つ
- 閉じ括弧「)」と波括弧「{」の間にはスペースを 1 つ
- else などが続く時はスペースを 1 つ

  ```
    if (true) {

    } else {

    }
  ```

<br>

## コメント

- 該当部分の上部に書く
- /\*\* \*/のように複数行コメントアウトする書き方で書く
- コメントが必要のないようなコードを書く
- 関数にはコメントを書くようにする
  ```
    /**
    * 引数aと引数bを加算する
    * @param {int} a
    * @param {int} b - 足す数
    *
    * @returns {int}
    */
  ```