git config user.name "MasonLiuChn"
git config user.email "MasonLiuChn@gmail.com"
export GIT_AUTHOR_DATE="2018-12-09T16:46:45+08:00"
export GIT_COMMITTER_DATE="2018-12-09T16:46:45+08:00"
git add . -A
git commit -m "update ui"
unset GIT_AUTHOR_DATE
unset GIT_COMMITTER_DATE
git push