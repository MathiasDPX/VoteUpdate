import requests
import os

bodyfile = open("body.md", "w+", encoding="utf-8")
GITHUB_REPO = os.environ.get("GITHUB_REPOSITORY", "MathiasDPX/VoteUpdate")
VERSION = os.environ.get("VERSION", "???")

r = requests.get(f"https://api.github.com/repos/{GITHUB_REPO}/releases/latest")

latest_release = "???"
if r.status_code == 200:
    response = r.json()
    latest_release = response.get("tag_name", "???")

bodyfile.write(f"**Full changelog**: https://github.com/{GITHUB_REPO}/compare/v{latest_release}...v{VERSION}")

bodyfile.close()