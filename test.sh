# To run all tests: "./test.sh"
# To run a specific test: "./test.sh <test_name>"

javac Library.java

if [[ "$#" -eq 0 ]]; then
    echo "--------------"
    for f in "test"/*.in; do
        echo "${f:0:-3}:"
        diff "${f:0:-3}.out" <(java Library < "${f:0:-3}.in")
        ret=$?
        if [[ $ret -eq 0 ]]; then
            echo ">>> PASSED"
        fi
        echo "--------------"
    done
elif [[ "$#" -eq 1 ]]; then
    echo "--------------"
    echo "test/$1"
    diff "test/$1.out" <(java Library < "test/$1.in")
    ret=$?
    if [[ $ret -eq 0 ]]; then
        echo ">>> PASSED"
    fi
    echo "--------------"
fi


