---
title: Idempotence In Data Pipelines
date: 2021-05-04 00:00
tags:
  - Data Pipelines
---

[[Idempotence]] in a data pipeline ensures that running the same task multiple times will generate the same final output.

Usually this is achieved by ensuring each "record", or item processed, contains a unique identifier allowing for an [[Upsert]] style of record insertion.

In my experience, data pipelines without idempotence are near impossible to operate at any significant scale of dataset size. 

A similar sentiment was shared in [Eric Lathrop's](https://ericlathrop.com/) blog post, [Idempotence Now Prevents Pain Later](https://ericlathrop.com/2021/04/idempotence-now-prevents-pain-later/), which describes a scenario where converting a billing cron script to an idempotent script significantly reduces the overhead in running the script.

Idempotence alone in a data pipeline does not guarantee a pipeline will not be resumable, where if a job fails it can be rerun without reprocessing things that have already been processed.